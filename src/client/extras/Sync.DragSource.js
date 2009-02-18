/**
 * Component rendering peer: DragSource
 */
Extras.Sync.DragSource = Core.extend(Echo.Render.ComponentSync, {
    
    $load: function() {
        Echo.Render.registerPeer("Extras.DragSource", this);
    },
    
    /**
     * The dragging element.  This element is created by cloning the rendered DIV (and its descendants), reducing their
     * opacity, and then absolutely positioning it adjacent the mouse cursor position.
     * @type Element
     */
    _dragDiv: null,
    
    /**
     * Rendered DIV element.
     * @type Element 
     */
    _div: null,
    
    /**
     * Overlay DIV which covers other elements (such as IFRAMEs) when dragging which may otherwise suppress events.
     * @type Element
     */
    _overlayDiv: null,
    
    _divOrigin: null,

    /**
     * Method reference to <code>_processMouseMove()</code>.
     * @type Function
     */
    _processMouseMoveRef: null,

    /**
     * Method reference to <code>_processMouseUp()</code>.
     * @type Function
     */
    _processMouseUpRef: null,

    $construct: function() {
        this._processMouseMoveRef = Core.method(this, this._processMouseMove);
        this._processMouseUpRef = Core.method(this, this._processMouseUp);
    },
    
    /**
     * Start a drag operation.
     */
    _dragStart: function() {
        this._dragStop();
        
        this._overlayDiv = document.createElement("div");
        this._overlayDiv.style.cssText = "position:absolute;z-index:32767;width:100%;height:100%;";
        Echo.Sync.FillImage.render(this.client.getResourceUrl("Echo", "resource/Transparent.gif"), this._overlayDiv);

        this._divOrigin = new Core.Web.Measure.Bounds(this._div);
        
        this._dragDiv = this._div.cloneNode(true);
        this._dragDiv.style.position = "absolute";
        this._dragDiv.style.position.top = this._divOrigin.left + "px";
        this._dragDiv.style.position.left = this._divOrigin.top + "px";
        this._setDragOpacity(0.2);
        this._overlayDiv.appendChild(this._dragDiv);

        document.body.appendChild(this._overlayDiv);

        Core.Web.Event.add(document.body, "mousemove", this._processMouseMoveRef, true);
        Core.Web.Event.add(document.body, "mouseup", this._processMouseUpRef, true);
    },
    
    /**
     * Stop a drag operation.
     */
    _dragStop: function() {
        Core.Web.Event.remove(document.body, "mousemove", this._processMouseMoveRef, true);
        Core.Web.Event.remove(document.body, "mouseup", this._processMouseUpRef, true);

        if (this._overlayDiv) {
            document.body.removeChild(this._overlayDiv);
            this._overlayDiv = null;
        }
        this._dragDiv = null;
    },
    
    _processMouseDown: function(e) {
        Core.Web.DOM.preventEventDefault(e);

        if (!this.client || !this.client.verifyInput(this.component)) {
            return;
        }
        
        this._dragStart();
    },
    
    _processMouseMove: function(e) {
    },
    
    _processMouseUp: function(e) {
        this._dragStop();
    },
    
    /** @see Echo.Render.ComponentSync#renderAdd */
    renderAdd: function(update, parentElement) {
        this._div = document.createElement("div");
        if (this.component.children.length > 0) {
            Echo.Render.renderComponentAdd(update, this.component.children[0], this._div);
        }

        Core.Web.Event.add(this._div, "mousedown", Core.method(this, this._processMouseDown), false);
        
        parentElement.appendChild(this._div);
    },
    
    /** @see Echo.Render.ComponentSync#renderDispose */
    renderDispose: function(update) {
        this._dragStop();
        Core.Web.Event.removeAll(this._div);

        this._dragDiv = null;
        this._div = null;
    },
    
    /** @see Echo.Render.ComponentSync#renderUpdate */
    renderUpdate: function(update) {
        var element = this._div;
        var containerElement = element.parentNode;
        Echo.Render.renderComponentDispose(update, update.parent);
        containerElement.removeChild(element);
        this.renderAdd(update, containerElement);
    },
    
    _setDragOpacity: function(value) {
        if (Core.Web.Env.NOT_SUPPORTED_CSS_OPACITY) {
            if (Core.Web.Env.PROPRIETARY_IE_OPACITY_FILTER_REQUIRED) {
                this._dragDiv.style.filter = "alpha(opacity=" + (value * 100) + ")";
            }
        } else {
            this._dragDiv.style.opacity = value;
        }
    }
});