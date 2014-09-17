try {
  angular.module('kawwa2');
} catch (err) {
  angular.module('kawwa2', []);
}
;
(function ($, undefined) {
  var uuid = 0, runiqueId = /^ui-id-\d+$/;
  $.ui = $.ui || {};
  $.extend($.ui, {
    version: '@VERSION',
    keyCode: {
      BACKSPACE: 8,
      COMMA: 188,
      DELETE: 46,
      DOWN: 40,
      END: 35,
      ENTER: 13,
      ESCAPE: 27,
      HOME: 36,
      LEFT: 37,
      NUMPAD_ADD: 107,
      NUMPAD_DECIMAL: 110,
      NUMPAD_DIVIDE: 111,
      NUMPAD_ENTER: 108,
      NUMPAD_MULTIPLY: 106,
      NUMPAD_SUBTRACT: 109,
      PAGE_DOWN: 34,
      PAGE_UP: 33,
      PERIOD: 190,
      RIGHT: 39,
      SPACE: 32,
      TAB: 9,
      UP: 38
    }
  });
  $.fn.extend({
    focus: function (orig) {
      return function (delay, fn) {
        return typeof delay === 'number' ? this.each(function () {
          var elem = this;
          setTimeout(function () {
            $(elem).focus();
            if (fn) {
              fn.call(elem);
            }
          }, delay);
        }) : orig.apply(this, arguments);
      };
    }($.fn.focus),
    scrollParent: function () {
      var scrollParent;
      if ($.ui.ie && /(static|relative)/.test(this.css('position')) || /absolute/.test(this.css('position'))) {
        scrollParent = this.parents().filter(function () {
          return /(relative|absolute|fixed)/.test($.css(this, 'position')) && /(auto|scroll)/.test($.css(this, 'overflow') + $.css(this, 'overflow-y') + $.css(this, 'overflow-x'));
        }).eq(0);
      } else {
        scrollParent = this.parents().filter(function () {
          return /(auto|scroll)/.test($.css(this, 'overflow') + $.css(this, 'overflow-y') + $.css(this, 'overflow-x'));
        }).eq(0);
      }
      return /fixed/.test(this.css('position')) || !scrollParent.length ? $(document) : scrollParent;
    },
    uniqueId: function () {
      return this.each(function () {
        if (!this.id) {
          this.id = 'ui-id-' + ++uuid;
        }
      });
    },
    removeUniqueId: function () {
      return this.each(function () {
        if (runiqueId.test(this.id)) {
          $(this).removeAttr('id');
        }
      });
    }
  });
  function focusable(element, isTabIndexNotNaN) {
    var map, mapName, img, nodeName = element.nodeName.toLowerCase();
    if ('area' === nodeName) {
      map = element.parentNode;
      mapName = map.name;
      if (!element.href || !mapName || map.nodeName.toLowerCase() !== 'map') {
        return false;
      }
      img = $('img[usemap=#' + mapName + ']')[0];
      return !!img && visible(img);
    }
    return (/input|select|textarea|button|object/.test(nodeName) ? !element.disabled : 'a' === nodeName ? element.href || isTabIndexNotNaN : isTabIndexNotNaN) && visible(element);
  }
  function visible(element) {
    return $.expr.filters.visible(element) && !$(element).parents().addBack().filter(function () {
      return $.css(this, 'visibility') === 'hidden';
    }).length;
  }
  $.extend($.expr[':'], {
    data: $.expr.createPseudo ? $.expr.createPseudo(function (dataName) {
      return function (elem) {
        return !!$.data(elem, dataName);
      };
    }) : function (elem, i, match) {
      return !!$.data(elem, match[3]);
    },
    focusable: function (element) {
      return focusable(element, !isNaN($.attr(element, 'tabindex')));
    },
    tabbable: function (element) {
      var tabIndex = $.attr(element, 'tabindex'), isTabIndexNaN = isNaN(tabIndex);
      return (isTabIndexNaN || tabIndex >= 0) && focusable(element, !isTabIndexNaN);
    }
  });
  if (!$('<a>').outerWidth(1).jquery) {
    $.each([
      'Width',
      'Height'
    ], function (i, name) {
      var side = name === 'Width' ? [
          'Left',
          'Right'
        ] : [
          'Top',
          'Bottom'
        ], type = name.toLowerCase(), orig = {
          innerWidth: $.fn.innerWidth,
          innerHeight: $.fn.innerHeight,
          outerWidth: $.fn.outerWidth,
          outerHeight: $.fn.outerHeight
        };
      function reduce(elem, size, border, margin) {
        $.each(side, function () {
          size -= parseFloat($.css(elem, 'padding' + this)) || 0;
          if (border) {
            size -= parseFloat($.css(elem, 'border' + this + 'Width')) || 0;
          }
          if (margin) {
            size -= parseFloat($.css(elem, 'margin' + this)) || 0;
          }
        });
        return size;
      }
      $.fn['inner' + name] = function (size) {
        if (size === undefined) {
          return orig['inner' + name].call(this);
        }
        return this.each(function () {
          $(this).css(type, reduce(this, size) + 'px');
        });
      };
      $.fn['outer' + name] = function (size, margin) {
        if (typeof size !== 'number') {
          return orig['outer' + name].call(this, size);
        }
        return this.each(function () {
          $(this).css(type, reduce(this, size, true, margin) + 'px');
        });
      };
    });
  }
  if (!$.fn.addBack) {
    $.fn.addBack = function (selector) {
      return this.add(selector == null ? this.prevObject : this.prevObject.filter(selector));
    };
  }
  if ($('<a>').data('a-b', 'a').removeData('a-b').data('a-b')) {
    $.fn.removeData = function (removeData) {
      return function (key) {
        if (arguments.length) {
          return removeData.call(this, $.camelCase(key));
        } else {
          return removeData.call(this);
        }
      };
    }($.fn.removeData);
  }
  $.ui.ie = !!/msie [\w.]+/.exec(navigator.userAgent.toLowerCase());
  $.support.selectstart = 'onselectstart' in document.createElement('div');
  $.fn.extend({
    disableSelection: function () {
      return this.bind(($.support.selectstart ? 'selectstart' : 'mousedown') + '.ui-disableSelection', function (event) {
        event.preventDefault();
      });
    },
    enableSelection: function () {
      return this.unbind('.ui-disableSelection');
    },
    zIndex: function (zIndex) {
      if (zIndex !== undefined) {
        return this.css('zIndex', zIndex);
      }
      if (this.length) {
        var elem = $(this[0]), position, value;
        while (elem.length && elem[0] !== document) {
          position = elem.css('position');
          if (position === 'absolute' || position === 'relative' || position === 'fixed') {
            value = parseInt(elem.css('zIndex'), 10);
            if (!isNaN(value) && value !== 0) {
              return value;
            }
          }
          elem = elem.parent();
        }
      }
      return 0;
    }
  });
  $.ui.plugin = {
    add: function (module, option, set) {
      var i, proto = $.ui[module].prototype;
      for (i in set) {
        proto.plugins[i] = proto.plugins[i] || [];
        proto.plugins[i].push([
          option,
          set[i]
        ]);
      }
    },
    call: function (instance, name, args, allowDisconnected) {
      var i, set = instance.plugins[name];
      if (!set) {
        return;
      }
      if (!allowDisconnected && (!instance.element[0].parentNode || instance.element[0].parentNode.nodeType === 11)) {
        return;
      }
      for (i = 0; i < set.length; i++) {
        if (instance.options[set[i][0]]) {
          set[i][1].apply(instance.element, args);
        }
      }
    }
  };
}(jQuery));
;
(function ($, undefined) {
  var uuid = 0, slice = Array.prototype.slice, _cleanData = $.cleanData;
  $.cleanData = function (elems) {
    for (var i = 0, elem; (elem = elems[i]) != null; i++) {
      try {
        $(elem).triggerHandler('remove');
      } catch (e) {
      }
    }
    _cleanData(elems);
  };
  $.widget = function (name, base, prototype) {
    var fullName, existingConstructor, constructor, basePrototype, proxiedPrototype = {}, namespace = name.split('.')[0];
    name = name.split('.')[1];
    fullName = namespace + '-' + name;
    if (!prototype) {
      prototype = base;
      base = $.Widget;
    }
    $.expr[':'][fullName.toLowerCase()] = function (elem) {
      return !!$.data(elem, fullName);
    };
    $[namespace] = $[namespace] || {};
    existingConstructor = $[namespace][name];
    constructor = $[namespace][name] = function (options, element) {
      if (!this._createWidget) {
        return new constructor(options, element);
      }
      if (arguments.length) {
        this._createWidget(options, element);
      }
    };
    $.extend(constructor, existingConstructor, {
      version: prototype.version,
      _proto: $.extend({}, prototype),
      _childConstructors: []
    });
    basePrototype = new base();
    basePrototype.options = $.widget.extend({}, basePrototype.options);
    $.each(prototype, function (prop, value) {
      if (!$.isFunction(value)) {
        proxiedPrototype[prop] = value;
        return;
      }
      proxiedPrototype[prop] = function () {
        var _super = function () {
            return base.prototype[prop].apply(this, arguments);
          }, _superApply = function (args) {
            return base.prototype[prop].apply(this, args);
          };
        return function () {
          var __super = this._super, __superApply = this._superApply, returnValue;
          this._super = _super;
          this._superApply = _superApply;
          returnValue = value.apply(this, arguments);
          this._super = __super;
          this._superApply = __superApply;
          return returnValue;
        };
      }();
    });
    constructor.prototype = $.widget.extend(basePrototype, { widgetEventPrefix: existingConstructor ? basePrototype.widgetEventPrefix : name }, proxiedPrototype, {
      constructor: constructor,
      namespace: namespace,
      widgetName: name,
      widgetFullName: fullName
    });
    if (existingConstructor) {
      $.each(existingConstructor._childConstructors, function (i, child) {
        var childPrototype = child.prototype;
        $.widget(childPrototype.namespace + '.' + childPrototype.widgetName, constructor, child._proto);
      });
      delete existingConstructor._childConstructors;
    } else {
      base._childConstructors.push(constructor);
    }
    $.widget.bridge(name, constructor);
  };
  $.widget.extend = function (target) {
    var input = slice.call(arguments, 1), inputIndex = 0, inputLength = input.length, key, value;
    for (; inputIndex < inputLength; inputIndex++) {
      for (key in input[inputIndex]) {
        value = input[inputIndex][key];
        if (input[inputIndex].hasOwnProperty(key) && value !== undefined) {
          if ($.isPlainObject(value)) {
            target[key] = $.isPlainObject(target[key]) ? $.widget.extend({}, target[key], value) : $.widget.extend({}, value);
          } else {
            target[key] = value;
          }
        }
      }
    }
    return target;
  };
  $.widget.bridge = function (name, object) {
    var fullName = object.prototype.widgetFullName || name;
    $.fn[name] = function (options) {
      var isMethodCall = typeof options === 'string', args = slice.call(arguments, 1), returnValue = this;
      options = !isMethodCall && args.length ? $.widget.extend.apply(null, [options].concat(args)) : options;
      if (isMethodCall) {
        this.each(function () {
          var methodValue, instance = $.data(this, fullName);
          if (options === 'instance') {
            returnValue = instance;
            return false;
          }
          if (!instance) {
            return $.error('cannot call methods on ' + name + ' prior to initialization; ' + 'attempted to call method \'' + options + '\'');
          }
          if (!$.isFunction(instance[options]) || options.charAt(0) === '_') {
            return $.error('no such method \'' + options + '\' for ' + name + ' widget instance');
          }
          methodValue = instance[options].apply(instance, args);
          if (methodValue !== instance && methodValue !== undefined) {
            returnValue = methodValue && methodValue.jquery ? returnValue.pushStack(methodValue.get()) : methodValue;
            return false;
          }
        });
      } else {
        this.each(function () {
          var instance = $.data(this, fullName);
          if (instance) {
            instance.option(options || {})._init();
          } else {
            $.data(this, fullName, new object(options, this));
          }
        });
      }
      return returnValue;
    };
  };
  $.Widget = function () {
  };
  $.Widget._childConstructors = [];
  $.Widget.prototype = {
    widgetName: 'widget',
    widgetEventPrefix: '',
    defaultElement: '<div>',
    options: {
      disabled: false,
      create: null
    },
    _createWidget: function (options, element) {
      element = $(element || this.defaultElement || this)[0];
      this.element = $(element);
      this.uuid = uuid++;
      this.eventNamespace = '.' + this.widgetName + this.uuid;
      this.options = $.widget.extend({}, this.options, this._getCreateOptions(), options);
      this.bindings = $();
      this.hoverable = $();
      this.focusable = $();
      if (element !== this) {
        $.data(element, this.widgetFullName, this);
        this._on(true, this.element, {
          remove: function (event) {
            if (event.target === element) {
              this.destroy();
            }
          }
        });
        this.document = $(element.style ? element.ownerDocument : element.document || element);
        this.window = $(this.document[0].defaultView || this.document[0].parentWindow);
      }
      this._create();
      this._trigger('create', null, this._getCreateEventData());
      this._init();
    },
    _getCreateOptions: $.noop,
    _getCreateEventData: $.noop,
    _create: $.noop,
    _init: $.noop,
    destroy: function () {
      this._destroy();
      this.element.unbind(this.eventNamespace).removeData(this.widgetName).removeData(this.widgetFullName).removeData($.camelCase(this.widgetFullName));
      this.widget().unbind(this.eventNamespace).removeAttr('aria-disabled').removeClass(this.widgetFullName + '-disabled ' + 'ui-state-disabled');
      this.bindings.unbind(this.eventNamespace);
      this.hoverable.removeClass('ui-state-hover');
      this.focusable.removeClass('ui-state-focus');
    },
    _destroy: $.noop,
    widget: function () {
      return this.element;
    },
    option: function (key, value) {
      var options = key, parts, curOption, i;
      if (arguments.length === 0) {
        return $.widget.extend({}, this.options);
      }
      if (typeof key === 'string') {
        options = {};
        parts = key.split('.');
        key = parts.shift();
        if (parts.length) {
          curOption = options[key] = $.widget.extend({}, this.options[key]);
          for (i = 0; i < parts.length - 1; i++) {
            curOption[parts[i]] = curOption[parts[i]] || {};
            curOption = curOption[parts[i]];
          }
          key = parts.pop();
          if (value === undefined) {
            return curOption[key] === undefined ? null : curOption[key];
          }
          curOption[key] = value;
        } else {
          if (value === undefined) {
            return this.options[key] === undefined ? null : this.options[key];
          }
          options[key] = value;
        }
      }
      this._setOptions(options);
      return this;
    },
    _setOptions: function (options) {
      var key;
      for (key in options) {
        this._setOption(key, options[key]);
      }
      return this;
    },
    _setOption: function (key, value) {
      this.options[key] = value;
      if (key === 'disabled') {
        this.widget().toggleClass(this.widgetFullName + '-disabled', !!value);
        this.hoverable.removeClass('ui-state-hover');
        this.focusable.removeClass('ui-state-focus');
      }
      return this;
    },
    enable: function () {
      return this._setOption('disabled', false);
    },
    disable: function () {
      return this._setOption('disabled', true);
    },
    _on: function (suppressDisabledCheck, element, handlers) {
      var delegateElement, instance = this;
      if (typeof suppressDisabledCheck !== 'boolean') {
        handlers = element;
        element = suppressDisabledCheck;
        suppressDisabledCheck = false;
      }
      if (!handlers) {
        handlers = element;
        element = this.element;
        delegateElement = this.widget();
      } else {
        element = delegateElement = $(element);
        this.bindings = this.bindings.add(element);
      }
      $.each(handlers, function (event, handler) {
        function handlerProxy() {
          if (!suppressDisabledCheck && (instance.options.disabled === true || $(this).hasClass('ui-state-disabled'))) {
            return;
          }
          return (typeof handler === 'string' ? instance[handler] : handler).apply(instance, arguments);
        }
        if (typeof handler !== 'string') {
          handlerProxy.guid = handler.guid = handler.guid || handlerProxy.guid || $.guid++;
        }
        var match = event.match(/^(\w+)\s*(.*)$/), eventName = match[1] + instance.eventNamespace, selector = match[2];
        if (selector) {
          delegateElement.delegate(selector, eventName, handlerProxy);
        } else {
          element.bind(eventName, handlerProxy);
        }
      });
    },
    _off: function (element, eventName) {
      eventName = (eventName || '').split(' ').join(this.eventNamespace + ' ') + this.eventNamespace;
      element.unbind(eventName).undelegate(eventName);
    },
    _delay: function (handler, delay) {
      function handlerProxy() {
        return (typeof handler === 'string' ? instance[handler] : handler).apply(instance, arguments);
      }
      var instance = this;
      return setTimeout(handlerProxy, delay || 0);
    },
    _hoverable: function (element) {
      this.hoverable = this.hoverable.add(element);
      this._on(element, {
        mouseenter: function (event) {
          $(event.currentTarget).addClass('ui-state-hover');
        },
        mouseleave: function (event) {
          $(event.currentTarget).removeClass('ui-state-hover');
        }
      });
    },
    _focusable: function (element) {
      this.focusable = this.focusable.add(element);
      this._on(element, {
        focusin: function (event) {
          $(event.currentTarget).addClass('ui-state-focus');
        },
        focusout: function (event) {
          $(event.currentTarget).removeClass('ui-state-focus');
        }
      });
    },
    _trigger: function (type, event, data) {
      var prop, orig, callback = this.options[type];
      data = data || {};
      event = $.Event(event);
      event.type = (type === this.widgetEventPrefix ? type : this.widgetEventPrefix + type).toLowerCase();
      event.target = this.element[0];
      orig = event.originalEvent;
      if (orig) {
        for (prop in orig) {
          if (!(prop in event)) {
            event[prop] = orig[prop];
          }
        }
      }
      this.element.trigger(event, data);
      return !($.isFunction(callback) && callback.apply(this.element[0], [event].concat(data)) === false || event.isDefaultPrevented());
    }
  };
  $.each({
    show: 'fadeIn',
    hide: 'fadeOut'
  }, function (method, defaultEffect) {
    $.Widget.prototype['_' + method] = function (element, options, callback) {
      if (typeof options === 'string') {
        options = { effect: options };
      }
      var hasOptions, effectName = !options ? method : options === true || typeof options === 'number' ? defaultEffect : options.effect || defaultEffect;
      options = options || {};
      if (typeof options === 'number') {
        options = { duration: options };
      }
      hasOptions = !$.isEmptyObject(options);
      options.complete = callback;
      if (options.delay) {
        element.delay(options.delay);
      }
      if (hasOptions && $.effects && $.effects.effect[effectName]) {
        element[method](options);
      } else if (effectName !== method && element[effectName]) {
        element[effectName](options.duration, options.easing, callback);
      } else {
        element.queue(function (next) {
          $(this)[method]();
          if (callback) {
            callback.call(element[0]);
          }
          next();
        });
      }
    };
  });
}(jQuery));
;
eval(function (p, a, c, k, e, r) {
  e = function (c) {
    return (c < a ? '' : e(parseInt(c / a))) + ((c = c % a) > 35 ? String.fromCharCode(c + 29) : c.toString(36));
  };
  if (!''.replace(/^/, String)) {
    while (c--)
      r[e(c)] = k[c] || e(c);
    k = [function (e) {
        return r[e];
      }];
    e = function () {
      return '\\w+';
    };
    c = 1;
  }
  ;
  while (c--)
    if (k[c])
      p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
  return p;
}(';5(1W.1C)(8($){5((!$.1s.1V&&!$.1s.1U))2d{1j.1X("1T",C,s)}1R(e){};$.o.4=8(j){5(3.u==0)9 3;5(M V[0]==\'1m\'){5(3.u>1){7 k=V;9 3.18(8(){$.o.4.K($(3),k)})};$.o.4[V[0]].K(3,$.27(V).26(1)||[]);9 3};7 j=$.1b({},$.o.4.1w,j||{});$.o.4.P++;3.1y(\'.l-4-1g\').p(\'l-4-1g\').18(8(){7 b,m=$(3);7 c=(3.2g||\'28-4\').1f(/\\[|\\]/g,\'Y\').1f(/^\\Y+|\\Y+$/g,\'\');7 d=$(3.2h||1j.1H);7 e=d.6(\'4\');5(!e||e.1o!=$.o.4.P)e={E:0,1o:$.o.4.P};7 f=e[c]||d.6(\'4\'+c);5(f)b=f.6(\'4\');5(f&&b)b.E++;R{b=$.1b({},j||{},($.1d?m.1d():($.25?m.6():w))||{},{E:0,L:[],v:[]});b.z=e.E++;f=$(\'<1G 13="l-4-1I"/>\');m.1J(f);f.p(\'4-12-11-10\');5(m.Z(\'G\')||m.14(\'G\'))b.n=s;5(m.14(\'1c\'))b.1c=s;f.1r(b.D=$(\'<W 13="4-D"><a U="\'+b.D+\'">\'+b.1B+\'</a></W>\').q(\'1e\',8(){$(3).4(\'N\');$(3).p(\'l-4-T\')}).q(\'1h\',8(){$(3).4(\'x\');$(3).I(\'l-4-T\')}).q(\'1i\',8(){$(3).4(\'y\')}).6(\'4\',b))};7 g=$(\'<W 20="21" 22-24="\'+3.U+\'" 13="l-4 t-\'+b.z+\'"><a U="\'+(3.U||3.1k)+\'">\'+3.1k+\'</a></W>\');f.1r(g);5(3.X)g.Z(\'X\',3.X);5(3.1x)g.p(3.1x);5(b.29)b.B=2;5(M b.B==\'1l\'&&b.B>0){7 h=($.o.15?g.15():0)||b.1n;7 i=(b.E%b.B),17=1K.1L(h/b.B);g.15(17).1M(\'a\').1N({\'1O-1P\':\'-\'+(i*17)+\'1Q\'})};5(b.n)g.p(\'l-4-1p\');R g.p(\'l-4-1S\').q(\'1e\',8(){$(3).4(\'1q\');$(3).4(\'J\')}).q(\'1h\',8(){$(3).4(\'x\');$(3).4(\'H\')}).q(\'1i\',8(){$(3).4(\'y\')});5(3.S)b.r=g;5(3.1Y=="A"){5($(3).14(\'1Z\'))b.r=g};m.1t();m.q(\'1u.4\',8(a){5(a.1v)9 C;$(3).4(\'y\')});g.6(\'4.m\',m.6(\'4.l\',g));b.L[b.L.u]=g[0];b.v[b.v.u]=m[0];b.t=e[c]=f;b.23=d;m.6(\'4\',b);f.6(\'4\',b);g.6(\'4\',b);d.6(\'4\',e);d.6(\'4\'+c,f)});$(\'.4-12-11-10\').4(\'x\').I(\'4-12-11-10\');9 3};$.1b($.o.4,{P:0,J:8(){7 a=3.6(\'4\');5(!a)9 3;5(!a.J)9 3;7 b=$(3).6(\'4.m\')||$(3.19==\'1a\'?3:w);5(a.J)a.J.K(b[0],[b.Q(),$(\'a\',b.6(\'4.l\'))[0]])},H:8(){7 a=3.6(\'4\');5(!a)9 3;5(!a.H)9 3;7 b=$(3).6(\'4.m\')||$(3.19==\'1a\'?3:w);5(a.H)a.H.K(b[0],[b.Q(),$(\'a\',b.6(\'4.l\'))[0]])},1q:8(){7 a=3.6(\'4\');5(!a)9 3;5(a.n)9;3.4(\'N\');3.1z().1A().O(\'.t-\'+a.z).p(\'l-4-T\')},N:8(){7 a=3.6(\'4\');5(!a)9 3;5(a.n)9;a.t.2a().O(\'.t-\'+a.z).I(\'l-4-q\').I(\'l-4-T\')},x:8(){7 a=3.6(\'4\');5(!a)9 3;3.4(\'N\');7 b=$(a.r);7 c=b.u?b.1z().1A().O(\'.t-\'+a.z):w;5(c)c.p(\'l-4-q\');a.D[a.n||a.1c?\'1t\':\'2b\']();3.2c()[a.n?\'p\':\'I\'](\'l-4-1p\')},y:8(a,b){7 c=3.6(\'4\');5(!c)9 3;5(c.n)9;c.r=w;5(M a!=\'F\'||3.u>1){5(M a==\'1l\')9 $(c.L[a]).4(\'y\',F,b);5(M a==\'1m\'){$.18(c.L,8(){5($(3).6(\'4.m\').Q()==a)$(3).4(\'y\',F,b)});9 3}}R{c.r=3[0].19==\'1a\'?3.6(\'4.l\'):(3.2e(\'.t-\'+c.z)?3:w)};3.6(\'4\',c);3.4(\'x\');7 d=$(c.r?c.r.6(\'4.m\'):w);7 e=$(c.v).O(\':S\');7 f=$(c.v).1y(d);f.1D(\'S\',C);d.1D(\'S\',s);$(d.u?d:e).2f({1E:\'1u\',1v:s});5((b||b==F)&&c.1F)c.1F.K(d[0],[d.Q(),$(\'a\',c.r)[0]]);9 3},n:8(a,b){7 c=3.6(\'4\');5(!c)9 3;c.n=a||a==F?s:C;5(b)$(c.v).Z("G","G");R $(c.v).2i("G");3.6(\'4\',c);3.4(\'x\')},2j:8(){3.4(\'n\',s,s)},2k:8(){3.4(\'n\',C,C)}});$.o.4.1w={D:\'2l 2m\',1B:\'\',B:0,1n:16};$(8(){$(\'m[1E=2n].l\').4()})})(1C);', 62, 148, '|||this|rating|if|data|var|function|return||||||||||||star|input|readOnly|fn|addClass|on|current|true|rater|length|inputs|null|draw|select|serial||split|false|cancel|count|undefined|disabled|blur|removeClass|focus|apply|stars|typeof|drain|filter|calls|val|else|checked|hover|title|arguments|div|id|_|attr|drawn|be|to|class|hasClass|width||spw|each|tagName|INPUT|extend|required|metadata|mouseover|replace|applied|mouseout|click|document|value|number|string|starWidth|call|readonly|fill|append|support|hide|change|selfTriggered|options|className|not|prevAll|addBack|cancelValue|jQuery|prop|type|callback|span|body|control|before|Math|floor|find|css|margin|left|px|catch|live|BackgroundImageCache|style|opacity|window|execCommand|nodeName|selected|role|text|aria|context|label|meta|slice|makeArray|unnamed|half|children|show|siblings|try|is|trigger|name|form|removeAttr|disable|enable|Cancel|Rating|radio'.split('|'), 0, {}));
;
(function ($) {
  $.fn.jcarousel = function (o) {
    if (typeof o == 'string') {
      var instance = $(this).data('jcarousel'), args = Array.prototype.slice.call(arguments, 1);
      return instance[o].apply(instance, args);
    } else
      return this.each(function () {
        $(this).data('jcarousel', new $jc(this, o));
      });
  };
  var defaults = {
      vertical: false,
      start: 1,
      offset: 1,
      size: null,
      scroll: 3,
      visible: null,
      animation: 'normal',
      easing: 'swing',
      auto: 0,
      wrap: null,
      initCallback: null,
      reloadCallback: null,
      itemLoadCallback: null,
      itemFirstInCallback: null,
      itemFirstOutCallback: null,
      itemLastInCallback: null,
      itemLastOutCallback: null,
      itemVisibleInCallback: null,
      itemVisibleOutCallback: null,
      itemSelectedCallback: null,
      buttonNextHTML: '<button class="ui-button ui-widget ui-state-default ui-button-icon-only" title="next"></button>',
      buttonPrevHTML: '<button class="ui-button ui-widget ui-state-default ui-button-icon-only" title="previous"></button>',
      buttonNextEvent: 'click',
      buttonPrevEvent: 'click',
      buttonNextCallback: null,
      buttonPrevCallback: null
    };
  $.jcarousel = function (e, o) {
    this.options = $.extend({}, defaults, o || {});
    this.locked = false;
    this.container = null;
    this.clip = null;
    this.list = null;
    this.buttonNext = null;
    this.buttonPrev = null;
    this.wh = !this.options.vertical ? 'width' : 'height';
    this.lt = !this.options.vertical ? 'left' : 'top';
    this.selectedIndex = 0;
    var skin = '', split = e.className.split(' ');
    for (var i = 0; i < split.length; i++) {
      if (split[i].indexOf('jcarousel-skin') != -1) {
        $(e).removeClass(split[i]);
        skin = split[i];
        break;
      }
    }
    if (e.nodeName == 'UL' || e.nodeName == 'OL') {
      this.list = $(e);
      this.list.attr('role', 'listbox');
      this.container = this.list.parent();
      if (this.container.hasClass('jcarousel-clip')) {
        if (!this.container.parent().hasClass('jcarousel-container'))
          this.container = this.container.wrap('<div></div>');
        this.container = this.container.parent();
      } else if (!this.container.hasClass('jcarousel-container'))
        this.container = this.list.wrap('<div></div>').parent();
    } else {
      this.container = $(e);
      this.list = this.container.find('ul,ol').eq(0);
    }
    this.list.focusin(function (event) {
      self.selectItem(event.target);
    });
    this.selectItem = function (target) {
      target = $(target);
      if (target.length == 0 || !target.hasClass('jcarousel-item'))
        return;
      var oldTarget = target.siblings('.jcarousel-item').filter('[tabindex=\'0\']');
      if (oldTarget.length > 0) {
        oldTarget.attr({
          'tabindex': '-1',
          'aria-selected': 'false'
        }).removeClass('selectedItem');
      }
      target.attr({
        'tabindex': '0',
        'aria-selected': 'true'
      }).addClass('selectedItem');
      if (this.options.itemSelectedCallback != null)
        this.options.itemSelectedCallback(this, target, parseInt(target.attr('jcarouselindex')));
    };
    this.list.keydown(function (event) {
      var target, newTarget, index, newIndex, forward, pageSize;
      if ([
          33,
          34,
          35,
          36,
          37,
          38,
          39,
          40
        ].indexOf(event.keyCode) == -1)
        return;
      var kC = event.keyCode;
      forward = kC == 33 || kC == 35 || kC == 39 || kC == 40;
      target = event.target;
      index = parseInt(target.getAttribute('jcarouselindex'));
      if (!index)
        return;
      switch (event.keyCode) {
      case 37:
      case 38:
      case 39:
      case 40:
        forward = kC == 38 || kC == 39;
        newIndex = forward ? index + 1 : index - 1;
        break;
      case 33:
      case 34:
        forward = kC == 33;
        pageSize = self.options.scroll;
        newIndex = forward ? index + pageSize : index - pageSize;
        break;
      case 35:
      case 36:
        forward = kC == 35;
        newIndex = forward ? self.size() : 1;
        break;
      }
      newIndex = Math.max(1, Math.min(self.size(), newIndex));
      if (!self.has(newIndex))
        return;
      newTarget = self.get(newIndex);
      if (newTarget.length == 0)
        return;
      self.selectedIndex = newIndex;
      var scrollIndex = forward ? newIndex : newIndex - 2;
      if (newIndex <= self.last && newIndex >= self.first) {
        newTarget.focus();
      } else
        self.scroll(scrollIndex, true, true);
      return false;
    });
    if (skin != '' && this.container.parent()[0].className.indexOf('jcarousel-skin') == -1)
      this.container.wrap('<div class=" ' + skin + '"></div>');
    this.clip = this.list.parent();
    if (!this.clip.length || !this.clip.hasClass('jcarousel-clip'))
      this.clip = this.list.wrap('<div></div>').parent();
    this.buttonNext = $('.jcarousel-next', this.container);
    if (this.buttonNext.size() == 0 && this.options.buttonNextHTML != null) {
      this.buttonNext = this.clip.after(this.options.buttonNextHTML).next();
      if ($.support.highContrast)
        this.buttonNext.append('<span>\u25b6</span>');
    }
    this.buttonNext.addClass(this.className('jcarousel-next'));
    this.buttonPrev = $('.jcarousel-prev', this.container);
    if (this.buttonPrev.size() == 0 && this.options.buttonPrevHTML != null) {
      this.buttonPrev = this.clip.before(this.options.buttonPrevHTML).prev();
      if ($.support.highContrast)
        this.buttonPrev.append('<span>\u25c0</span>');
    }
    this.buttonPrev.addClass(this.className('jcarousel-prev'));
    this.clip.addClass(this.className('jcarousel-clip')).css({
      overflow: 'hidden',
      position: 'relative'
    });
    this.list.addClass(this.className('jcarousel-list')).css({
      overflow: 'hidden',
      position: 'relative',
      top: 0,
      left: 0,
      margin: 0,
      padding: 0
    });
    this.container.addClass(this.className('jcarousel-container')).css({ position: 'relative' });
    var di = this.options.visible != null ? Math.ceil(this.clipping() / this.options.visible) : null;
    var li = this.list.children('li');
    var self = this;
    if (li.size() > 0) {
      var wh = 0, i = this.options.offset;
      li.each(function () {
        self.format(this, i++);
        wh += self.dimension(this, di);
      });
      this.list.css(this.wh, wh + 'px');
      if (!o || o.size === undefined)
        this.options.size = li.size();
    }
    this.container.css('display', 'block');
    this.buttonNext.css('display', 'block');
    this.buttonPrev.css('display', 'block');
    this.funcNext = function () {
      self.next();
    };
    this.funcPrev = function () {
      self.prev();
    };
    this.funcResize = function () {
      self.reload();
    };
    if (this.options.initCallback != null)
      this.options.initCallback(this, 'init');
    if (false) {
      this.buttons(false, false);
      $(window).bind('load.jcarousel', function () {
        self.setup();
      });
    } else
      this.setup();
  };
  var $jc = $.jcarousel;
  $jc.fn = $jc.prototype = { jcarousel: '0.2.4' };
  $jc.fn.extend = $jc.extend = $.extend;
  $jc.fn.extend({
    setup: function () {
      this.first = null;
      this.last = null;
      this.prevFirst = null;
      this.prevLast = null;
      this.animating = false;
      this.timer = null;
      this.tail = null;
      this.inTail = false;
      if (this.locked)
        return;
      this.list.css(this.lt, this.pos(this.options.offset) + 'px');
      var p = this.pos(this.options.start);
      this.prevFirst = this.prevLast = null;
      this.animate(p, false);
      this.buttonNext.bind(this.options.buttonNextEvent + '.jcarousel', this.funcNext);
      this.buttonPrev.bind(this.options.buttonPrevEvent + '.jcarousel', this.funcPrev);
      $(window).unbind('resize.jcarousel', this.funcResize).bind('resize.jcarousel', this.funcResize);
    },
    reset: function () {
      this.list.empty();
      this.list.css(this.lt, '0px');
      this.list.css(this.wh, '10px');
      if (this.options.initCallback != null)
        this.options.initCallback(this, 'reset');
      this.setup();
    },
    reload: function () {
      if (this.tail != null && this.inTail)
        this.list.css(this.lt, $jc.intval(this.list.css(this.lt)) + this.tail);
      this.tail = null;
      this.inTail = false;
      if (this.options.reloadCallback != null)
        this.options.reloadCallback(this);
      if (this.options.visible != null) {
        var self = this;
        var di = Math.ceil(this.clipping() / this.options.visible), wh = 0, lt = 0;
        $('li', this.list).each(function (i) {
          wh += self.dimension(this, di);
          if (i + 1 < self.first)
            lt = wh;
        });
        this.list.css(this.wh, wh + 'px');
        this.list.css(this.lt, -lt + 'px');
      }
      this.scroll(this.first, false);
    },
    lock: function () {
      this.locked = true;
      this.buttons();
    },
    unlock: function () {
      this.locked = false;
      this.buttons();
    },
    size: function (s) {
      if (s != undefined) {
        this.options.size = s;
        if (!this.locked)
          this.buttons();
      }
      return this.options.size;
    },
    has: function (i, i2) {
      if (i2 == undefined || !i2)
        i2 = i;
      if (this.options.size !== null && i2 > this.options.size)
        i2 = this.options.size;
      for (var j = i; j <= i2; j++) {
        var e = this.get(j);
        if (!e.length || e.hasClass('jcarousel-item-placeholder'))
          return false;
      }
      return true;
    },
    get: function (i) {
      return $('.jcarousel-item-' + i, this.list);
    },
    add: function (i, s) {
      var e = this.get(i), old = 0, add = 0;
      if (e.length == 0) {
        var c, e = this.create(i), j = $jc.intval(i);
        while (c = this.get(--j)) {
          if (j <= 0 || c.length) {
            j <= 0 ? this.list.prepend(e) : c.after(e);
            break;
          }
        }
      } else
        old = this.dimension(e);
      e.removeClass(this.className('jcarousel-item-placeholder'));
      typeof s == 'string' ? e.html(s) : e.empty().append(s);
      var di = this.options.visible != null ? Math.ceil(this.clipping() / this.options.visible) : null;
      var wh = this.dimension(e, di) - old;
      if (i > 0 && i < this.first)
        this.list.css(this.lt, $jc.intval(this.list.css(this.lt)) - wh + 'px');
      this.list.css(this.wh, $jc.intval(this.list.css(this.wh)) + wh + 'px');
      return e;
    },
    remove: function (i) {
      var e = this.get(i);
      if (!e.length || i >= this.first && i <= this.last)
        return;
      var d = this.dimension(e);
      if (i < this.first)
        this.list.css(this.lt, $jc.intval(this.list.css(this.lt)) + d + 'px');
      e.remove();
      this.list.css(this.wh, $jc.intval(this.list.css(this.wh)) - d + 'px');
    },
    next: function () {
      var newIndex;
      this.stopAuto();
      if (this.tail != null && !this.inTail)
        this.scrollTail(false);
      else {
        newIndex = (this.options.wrap == 'both' || this.options.wrap == 'last') && this.options.size != null && this.last == this.options.size ? 1 : this.first + this.options.scroll;
        newIndex = Math.max(1, Math.min(this.size(), newIndex));
        this.selectItem(this.get(newIndex));
        this.scroll(newIndex);
      }
    },
    prev: function () {
      var newIndex;
      this.stopAuto();
      if (this.tail != null && this.inTail)
        this.scrollTail(true);
      else {
        newIndex = (this.options.wrap == 'both' || this.options.wrap == 'first') && this.options.size != null && this.first == 1 ? this.options.size : this.first - this.options.scroll;
        newIndex = Math.max(1, Math.min(this.size(), newIndex));
        this.selectItem(this.get(newIndex));
        this.scroll(newIndex);
      }
    },
    scrollTail: function (b) {
      if (this.locked || this.animating || !this.tail)
        return;
      var pos = $jc.intval(this.list.css(this.lt));
      !b ? pos -= this.tail : pos += this.tail;
      this.inTail = !b;
      this.prevFirst = this.first;
      this.prevLast = this.last;
      this.animate(pos);
    },
    scroll: function (i, a, moveFocus) {
      if (this.locked || this.animating)
        return;
      this.animate(this.pos(i), a, moveFocus);
    },
    pos: function (i) {
      var pos = $jc.intval(this.list.css(this.lt));
      if (this.locked || this.animating)
        return pos;
      if (this.options.wrap != 'circular')
        i = i < 1 ? 1 : this.options.size && i > this.options.size ? this.options.size : i;
      var back = this.first > i;
      var f = this.options.wrap != 'circular' && this.first <= 1 ? 1 : this.first;
      var c = back ? this.get(f) : this.get(this.last);
      var j = back ? f : f - 1;
      var e = null, l = 0, p = false, d = 0, g;
      while (back ? --j >= i : ++j < i) {
        e = this.get(j);
        p = !e.length;
        if (e.length == 0) {
          e = this.create(j).addClass(this.className('jcarousel-item-placeholder'));
          c[back ? 'before' : 'after'](e);
          if (this.first != null && this.options.wrap == 'circular' && this.options.size !== null && (j <= 0 || j > this.options.size)) {
            g = this.get(this.index(j));
            if (g.length)
              this.add(j, g.children().clone(true));
          }
        }
        c = e;
        d = this.dimension(e);
        if (p)
          l += d;
        if (this.first != null && (this.options.wrap == 'circular' || j >= 1 && (this.options.size == null || j <= this.options.size)))
          pos = back ? pos + d : pos - d;
      }
      var clipping = this.clipping();
      var cache = [];
      var visible = 0, j = i, v = 0;
      var c = this.get(i - 1);
      while (++visible) {
        e = this.get(j);
        p = !e.length;
        if (e.length == 0) {
          e = this.create(j).addClass(this.className('jcarousel-item-placeholder'));
          c.length == 0 ? this.list.prepend(e) : c[back ? 'before' : 'after'](e);
          if (this.first != null && this.options.wrap == 'circular' && this.options.size !== null && (j <= 0 || j > this.options.size)) {
            g = this.get(this.index(j));
            if (g.length)
              this.add(j, g.find('>*').clone(true));
          }
        }
        c = e;
        var d = this.dimension(e);
        if (d == 0) {
          return 0;
        }
        if (this.options.wrap != 'circular' && this.options.size !== null && j > this.options.size)
          cache.push(e);
        else if (p)
          l += d;
        v += d;
        if (v >= clipping)
          break;
        j++;
      }
      for (var x = 0; x < cache.length; x++)
        cache[x].remove();
      if (l > 0) {
        this.list.css(this.wh, this.dimension(this.list) + l + 'px');
        if (back) {
          pos -= l;
          this.list.css(this.lt, $jc.intval(this.list.css(this.lt)) - l + 'px');
        }
      }
      var last = i + visible - 1;
      if (this.options.wrap != 'circular' && this.options.size && last > this.options.size)
        last = this.options.size;
      if (j > last) {
        visible = 0, j = last, v = 0;
        while (++visible) {
          var e = this.get(j--);
          if (!e.length)
            break;
          v += this.dimension(e);
          if (v >= clipping)
            break;
        }
      }
      var first = last - visible + 1;
      if (this.options.wrap != 'circular' && first < 1)
        first = 1;
      if (this.inTail && back) {
        pos += this.tail;
        this.inTail = false;
      }
      this.tail = null;
      if (this.options.wrap != 'circular' && last == this.options.size && last - visible + 1 >= 1) {
        var m = $jc.margin(this.get(last), !this.options.vertical ? 'marginRight' : 'marginBottom');
        if (v - m > clipping)
          this.tail = v - clipping - m;
      }
      while (i-- > first)
        pos += this.dimension(this.get(i));
      this.prevFirst = this.first;
      this.prevLast = this.last;
      this.first = first;
      this.last = last;
      return pos;
    },
    animate: function (p, a, moveFocus) {
      if (this.locked || this.animating)
        return;
      this.animating = true;
      var self = this;
      var scrolled = function () {
        self.animating = false;
        if (p == 0)
          self.list.css(self.lt, 0);
        if (self.options.wrap == 'circular' || self.options.wrap == 'both' || self.options.wrap == 'last' || self.options.size == null || self.last < self.options.size)
          self.startAuto();
        self.buttons();
        self.notify('onAfterAnimation');
        if (moveFocus) {
          var focusTarget = self.get(self.selectedIndex);
          if (focusTarget)
            focusTarget.focus();
        }
      };
      this.notify('onBeforeAnimation');
      if (!this.options.animation || a == false) {
        this.list.css(this.lt, p + 'px');
        scrolled();
      } else {
        var o = !this.options.vertical ? { 'left': p } : { 'top': p };
        this.list.animate(o, this.options.animation, this.options.easing, scrolled);
      }
    },
    startAuto: function (s) {
      if (s != undefined)
        this.options.auto = s;
      if (this.options.auto == 0)
        return this.stopAuto();
      if (this.timer != null)
        return;
      var self = this;
      this.timer = setTimeout(function () {
        self.next();
      }, this.options.auto * 1000);
    },
    stopAuto: function () {
      if (this.timer == null)
        return;
      clearTimeout(this.timer);
      this.timer = null;
    },
    buttons: function (n, p) {
      if (n == undefined || n == null) {
        var n = !this.locked && this.options.size !== 0 && (this.options.wrap && this.options.wrap != 'first' || this.options.size == null || this.last < this.options.size);
        if (!this.locked && (!this.options.wrap || this.options.wrap == 'first') && this.options.size != null && this.last >= this.options.size)
          n = this.tail != null && !this.inTail;
      }
      if (p == undefined || p == null) {
        var p = !this.locked && this.options.size !== 0 && (this.options.wrap && this.options.wrap != 'last' || this.first > 1);
        if (!this.locked && (!this.options.wrap || this.options.wrap == 'last') && this.options.size != null && this.first == 1)
          p = this.tail != null && this.inTail;
      }
      var self = this;
      this.buttonNext[n ? 'removeClass' : 'addClass'](this.className('jcarousel-next-disabled'));
      if (!n)
        this.buttonNext.attr('disabled', 'disabled');
      else
        this.buttonNext.removeAttr('disabled');
      this.buttonPrev[p ? 'removeClass' : 'addClass'](this.className('jcarousel-prev-disabled')).attr('disabled', p ? 'false' : 'true');
      if (!p)
        this.buttonPrev.attr('disabled', 'disabled');
      else
        this.buttonPrev.removeAttr('disabled');
      if (this.buttonNext.length > 0 && (this.buttonNext[0].jcarouselstate == undefined || this.buttonNext[0].jcarouselstate != n) && this.options.buttonNextCallback != null) {
        this.buttonNext.each(function () {
          self.options.buttonNextCallback(self, this, n);
        });
        this.buttonNext[0].jcarouselstate = n;
      }
      if (this.buttonPrev.length > 0 && (this.buttonPrev[0].jcarouselstate == undefined || this.buttonPrev[0].jcarouselstate != p) && this.options.buttonPrevCallback != null) {
        this.buttonPrev.each(function () {
          self.options.buttonPrevCallback(self, this, p);
        });
        this.buttonPrev[0].jcarouselstate = p;
      }
    },
    notify: function (evt) {
      var state = this.prevFirst == null ? 'init' : this.prevFirst < this.first ? 'next' : 'prev';
      this.callback('itemLoadCallback', evt, state);
      if (this.prevFirst !== this.first) {
        this.callback('itemFirstInCallback', evt, state, this.first);
        this.callback('itemFirstOutCallback', evt, state, this.prevFirst);
      }
      if (this.prevLast !== this.last) {
        this.callback('itemLastInCallback', evt, state, this.last);
        this.callback('itemLastOutCallback', evt, state, this.prevLast);
      }
      this.callback('itemVisibleInCallback', evt, state, this.first, this.last, this.prevFirst, this.prevLast);
      this.callback('itemVisibleOutCallback', evt, state, this.prevFirst, this.prevLast, this.first, this.last);
    },
    callback: function (cb, evt, state, i1, i2, i3, i4) {
      if (this.options[cb] == undefined || typeof this.options[cb] != 'object' && evt != 'onAfterAnimation')
        return;
      var callback = typeof this.options[cb] == 'object' ? this.options[cb][evt] : this.options[cb];
      if (!$.isFunction(callback))
        return;
      var self = this;
      if (i1 === undefined)
        callback(self, state, evt);
      else if (i2 === undefined)
        this.get(i1).each(function () {
          callback(self, this, i1, state, evt);
        });
      else {
        for (var i = i1; i <= i2; i++)
          if (i !== null && !(i >= i3 && i <= i4))
            this.get(i).each(function () {
              callback(self, this, i, state, evt);
            });
      }
    },
    create: function (i) {
      return this.format('<li role="option" aria-selected="false"></li>', i);
    },
    format: function (e, i) {
      var $e = $(e).addClass(this.className('jcarousel-item')).addClass(this.className('jcarousel-item-' + i)).css({
          'float': 'left',
          'list-style': 'none'
        });
      $e.attr('jcarouselindex', i);
      $e.attr('tabindex', i - 1 == 0 ? '0' : '-1');
      $e.attr('role', 'option');
      $e.attr('aria-selected', i - 1 == 0 ? 'true' : 'false');
      if (i - 1 == 0)
        $e.addClass('selectedItem');
      return $e;
    },
    className: function (c) {
      return c + ' ' + c + (!this.options.vertical ? '-horizontal' : '-vertical');
    },
    dimension: function (e, d) {
      var el = e.jquery != undefined ? e[0] : e;
      var old = !this.options.vertical ? el.offsetWidth + $jc.margin(el, 'marginLeft') + $jc.margin(el, 'marginRight') : el.offsetHeight + $jc.margin(el, 'marginTop') + $jc.margin(el, 'marginBottom');
      if (d == undefined || old == d)
        return old;
      var w = !this.options.vertical ? d - $jc.margin(el, 'marginLeft') - $jc.margin(el, 'marginRight') : d - $jc.margin(el, 'marginTop') - $jc.margin(el, 'marginBottom');
      $(el).css(this.wh, w + 'px');
      return this.dimension(el);
    },
    clipping: function () {
      return !this.options.vertical ? this.clip[0].offsetWidth - $jc.intval(this.clip.css('borderLeftWidth')) - $jc.intval(this.clip.css('borderRightWidth')) : this.clip[0].offsetHeight - $jc.intval(this.clip.css('borderTopWidth')) - $jc.intval(this.clip.css('borderBottomWidth'));
    },
    index: function (i, s) {
      if (s == undefined)
        s = this.options.size;
      return Math.round(((i - 1) / s - Math.floor((i - 1) / s)) * s) + 1;
    }
  });
  $jc.extend({
    defaults: function (d) {
      return $.extend(defaults, d || {});
    },
    margin: function (e, p) {
      if (!e)
        return 0;
      var el = e.jquery != undefined ? e[0] : e;
      if (p == 'marginRight' && $.browser.safari) {
        var old = {
            'display': 'block',
            'float': 'none',
            'width': 'auto'
          }, oWidth, oWidth2;
        $.swap(el, old, function () {
          oWidth = el.offsetWidth;
        });
        old['marginRight'] = 0;
        $.swap(el, old, function () {
          oWidth2 = el.offsetWidth;
        });
        return oWidth2 - oWidth;
      }
      return $jc.intval($.css(el, p));
    },
    intval: function (v) {
      v = parseInt(v);
      return isNaN(v) ? 0 : v;
    }
  });
}(jQuery));
;
(function ($) {
  var isIE6 = $.browser.msie && $.browser.version < 7;
  var body = $(document.body);
  var window = $(window);
  var jqzoompluging_disabled = false;
  $.fn.jqzoom = function (options) {
    return this.each(function () {
      var node = this.nodeName.toLowerCase();
      if (node == 'a') {
        new jqzoom(this, options);
      }
    });
  };
  jqzoom = function (el, options) {
    var api = null;
    api = $(el).data('jqzoom');
    if (api)
      return api;
    var obj = this;
    var settings = $.extend({}, $.jqzoom.defaults, options || {});
    obj.el = el;
    el.rel = $(el).attr('rel');
    el.zoom_active = false;
    el.zoom_disabled = false;
    el.largeimageloading = false;
    el.largeimageloaded = false;
    el.scale = {};
    el.timer = null;
    el.mousepos = {};
    el.mouseDown = false;
    $(el).css({
      'outline-style': 'none',
      'text-decoration': 'none'
    });
    var img = $('img:eq(0)', el);
    el.title = $(el).attr('title');
    el.imagetitle = img.attr('title');
    var zoomtitle = $.trim(el.title).length > 0 ? el.title : el.imagetitle;
    var smallimage = new Smallimage(img);
    var lens = new Lens();
    var stage = new Stage();
    var largeimage = new Largeimage();
    var loader = new Loader();
    $(el).bind('click', function (e) {
      e.preventDefault();
      return false;
    });
    var zoomtypes = [
        'standard',
        'drag',
        'innerzoom',
        'reverse'
      ];
    if ($.inArray($.trim(settings.zoomType), zoomtypes) < 0) {
      settings.zoomType = 'standard';
    }
    $.extend(obj, {
      create: function () {
        if ($('.zoomPad', el).length == 0) {
          el.zoomPad = $('<div/>').addClass('zoomPad');
          img.wrap(el.zoomPad);
        }
        if (settings.zoomType == 'innerzoom') {
          settings.zoomWidth = smallimage.w;
          settings.zoomHeight = smallimage.h;
        }
        if ($('.zoomPup', el).length == 0) {
          lens.append();
        }
        if ($('.zoomWindow', el).length == 0) {
          stage.append();
        }
        if ($('.zoomPreload', el).length == 0) {
          loader.append();
        }
        if (settings.preloadImages || settings.zoomType == 'drag' || settings.alwaysOn) {
          obj.load();
        }
        obj.init();
      },
      init: function () {
        if (settings.zoomType == 'drag') {
          $('.zoomPad', el).mousedown(function () {
            el.mouseDown = true;
          });
          $('.zoomPad', el).mouseup(function () {
            el.mouseDown = false;
          });
          document.body.ondragstart = function () {
            return false;
          };
          $('.zoomPad', el).css({ cursor: 'default' });
          $('.zoomPup', el).css({ cursor: 'move' });
        }
        if (settings.zoomType == 'innerzoom') {
          $('.zoomWrapper', el).css({ cursor: 'crosshair' });
        }
        $('.zoomPad', el).bind('mouseenter mouseover', function (event) {
          img.attr('title', '');
          $(el).attr('title', '');
          el.zoom_active = true;
          smallimage.fetchdata();
          if (el.largeimageloaded) {
            obj.activate(event);
          } else {
            obj.load();
          }
        });
        $('.zoomPad', el).bind('mouseleave', function (event) {
          obj.deactivate();
        });
        $('.zoomPad', el).bind('mousemove', function (e) {
          if (e.pageX > smallimage.pos.r || e.pageX < smallimage.pos.l || e.pageY < smallimage.pos.t || e.pageY > smallimage.pos.b) {
            lens.setcenter();
            return false;
          }
          el.zoom_active = true;
          if (el.largeimageloaded && !$('.zoomWindow', el).is(':visible')) {
            obj.activate(e);
          }
          if (el.largeimageloaded && (settings.zoomType != 'drag' || settings.zoomType == 'drag' && el.mouseDown)) {
            lens.setposition(e);
          }
        });
        var thumb_preload = new Array();
        var i = 0;
        var thumblist = new Array();
        thumblist = $('a').filter(function () {
          var regex = new RegExp('gallery[\\s]*:[\\s]*\'' + $.trim(el.rel) + '\'', 'i');
          var rel = $(this).attr('rel');
          if (regex.test(rel)) {
            return this;
          }
        });
        if (thumblist.length > 0) {
          var first = thumblist.splice(0, 1);
          thumblist.push(first);
        }
        thumblist.each(function () {
          if (settings.preloadImages) {
            var thumb_options = $.extend({}, eval('(' + $.trim($(this).attr('rel')) + ')'));
            thumb_preload[i] = new Image();
            thumb_preload[i].src = thumb_options.largeimage;
            i++;
          }
          $(this).click(function (e) {
            if ($(this).hasClass('zoomThumbActive')) {
              return false;
            }
            thumblist.each(function () {
              $(this).removeClass('zoomThumbActive');
            });
            e.preventDefault();
            obj.swapimage(this);
            return false;
          });
        });
      },
      load: function () {
        if (el.largeimageloaded == false && el.largeimageloading == false) {
          var url = $(el).attr('href');
          el.largeimageloading = true;
          largeimage.loadimage(url);
        }
      },
      activate: function (e) {
        clearTimeout(el.timer);
        lens.show();
        stage.show();
      },
      deactivate: function (e) {
        switch (settings.zoomType) {
        case 'drag':
          break;
        default:
          img.attr('title', el.imagetitle);
          $(el).attr('title', el.title);
          if (settings.alwaysOn) {
            lens.setcenter();
          } else {
            stage.hide();
            lens.hide();
          }
          break;
        }
        el.zoom_active = false;
      },
      swapimage: function (link) {
        el.largeimageloading = false;
        el.largeimageloaded = false;
        var options = new Object();
        options = $.extend({}, eval('(' + $.trim($(link).attr('rel')) + ')'));
        if (options.smallimage && options.largeimage) {
          var smallimage = options.smallimage;
          var largeimage = options.largeimage;
          $(link).addClass('zoomThumbActive');
          $(el).attr('href', largeimage);
          img.attr('src', smallimage);
          lens.hide();
          stage.hide();
          obj.load();
        } else {
          alert('ERROR :: Missing parameter for largeimage or smallimage.');
          throw 'ERROR :: Missing parameter for largeimage or smallimage.';
        }
        return false;
      }
    });
    if (img[0].complete) {
      smallimage.fetchdata();
      if ($('.zoomPad', el).length == 0)
        obj.create();
    }
    function Smallimage(image) {
      var $obj = this;
      this.node = image[0];
      this.findborder = function () {
        var bordertop = 0;
        bordertop = image.css('border-top-width');
        btop = '';
        var borderleft = 0;
        borderleft = image.css('border-left-width');
        bleft = '';
        if (bordertop) {
          for (i = 0; i < 3; i++) {
            var x = [];
            x = bordertop.substr(i, 1);
            if (isNaN(x) == false) {
              btop = btop + '' + bordertop.substr(i, 1);
            } else {
              break;
            }
          }
        }
        if (borderleft) {
          for (i = 0; i < 3; i++) {
            if (!isNaN(borderleft.substr(i, 1))) {
              bleft = bleft + borderleft.substr(i, 1);
            } else {
              break;
            }
          }
        }
        $obj.btop = btop.length > 0 ? eval(btop) : 0;
        $obj.bleft = bleft.length > 0 ? eval(bleft) : 0;
      };
      this.fetchdata = function () {
        $obj.findborder();
        $obj.w = image.width();
        $obj.h = image.height();
        $obj.ow = image.outerWidth();
        $obj.oh = image.outerHeight();
        $obj.pos = image.offset();
        $obj.pos.l = image.offset().left + $obj.bleft;
        $obj.pos.t = image.offset().top + $obj.btop;
        $obj.pos.r = $obj.w + $obj.pos.l;
        $obj.pos.b = $obj.h + $obj.pos.t;
        $obj.rightlimit = image.offset().left + $obj.ow;
        $obj.bottomlimit = image.offset().top + $obj.oh;
      };
      this.node.onerror = function () {
        alert('Problems while loading image.');
        throw 'Problems while loading image.';
      };
      this.node.onload = function () {
        $obj.fetchdata();
        if ($('.zoomPad', el).length == 0)
          obj.create();
      };
      return $obj;
    }
    ;
    function Loader() {
      var $obj = this;
      this.append = function () {
        this.node = $('<div/>').addClass('zoomPreload').css('visibility', 'hidden').html(settings.preloadText);
        $('.zoomPad', el).append(this.node);
      };
      this.show = function () {
        this.node.top = (smallimage.oh - this.node.height()) / 2;
        this.node.left = (smallimage.ow - this.node.width()) / 2;
        this.node.css({
          top: this.node.top,
          left: this.node.left,
          position: 'absolute',
          visibility: 'visible'
        });
      };
      this.hide = function () {
        this.node.css('visibility', 'hidden');
      };
      return this;
    }
    function Lens() {
      var $obj = this;
      this.node = $('<div/>').addClass('zoomPup');
      this.append = function () {
        $('.zoomPad', el).append($(this.node).hide());
        if (settings.zoomType == 'reverse') {
          this.image = new Image();
          this.image.src = smallimage.node.src;
          $(this.node).empty().append(this.image);
        }
      };
      this.setdimensions = function () {
        this.node.w = parseInt(settings.zoomWidth / el.scale.x) > smallimage.w ? smallimage.w : parseInt(settings.zoomWidth / el.scale.x);
        this.node.h = parseInt(settings.zoomHeight / el.scale.y) > smallimage.h ? smallimage.h : parseInt(settings.zoomHeight / el.scale.y);
        this.node.top = (smallimage.oh - this.node.h - 2) / 2;
        this.node.left = (smallimage.ow - this.node.w - 2) / 2;
        this.node.css({
          top: 0,
          left: 0,
          width: this.node.w + 'px',
          height: this.node.h + 'px',
          position: 'absolute',
          display: 'none',
          borderWidth: 1 + 'px'
        });
        if (settings.zoomType == 'reverse') {
          this.image.src = smallimage.node.src;
          $(this.node).css({ 'opacity': 1 });
          $(this.image).css({
            position: 'absolute',
            display: 'block',
            left: -(this.node.left + 1 - smallimage.bleft) + 'px',
            top: -(this.node.top + 1 - smallimage.btop) + 'px'
          });
        }
      };
      this.setcenter = function () {
        this.node.top = (smallimage.oh - this.node.h - 2) / 2;
        this.node.left = (smallimage.ow - this.node.w - 2) / 2;
        this.node.css({
          top: this.node.top,
          left: this.node.left
        });
        if (settings.zoomType == 'reverse') {
          $(this.image).css({
            position: 'absolute',
            display: 'block',
            left: -(this.node.left + 1 - smallimage.bleft) + 'px',
            top: -(this.node.top + 1 - smallimage.btop) + 'px'
          });
        }
        largeimage.setposition();
      };
      this.setposition = function (e) {
        el.mousepos.x = e.pageX;
        el.mousepos.y = e.pageY;
        var lensleft = 0;
        var lenstop = 0;
        function overleft(lens) {
          return el.mousepos.x - lens.w / 2 < smallimage.pos.l;
        }
        function overright(lens) {
          return el.mousepos.x + lens.w / 2 > smallimage.pos.r;
        }
        function overtop(lens) {
          return el.mousepos.y - lens.h / 2 < smallimage.pos.t;
        }
        function overbottom(lens) {
          return el.mousepos.y + lens.h / 2 > smallimage.pos.b;
        }
        lensleft = el.mousepos.x + smallimage.bleft - smallimage.pos.l - (this.node.w + 2) / 2;
        lenstop = el.mousepos.y + smallimage.btop - smallimage.pos.t - (this.node.h + 2) / 2;
        if (overleft(this.node)) {
          lensleft = smallimage.bleft - 1;
        } else if (overright(this.node)) {
          lensleft = smallimage.w + smallimage.bleft - this.node.w - 1;
        }
        if (overtop(this.node)) {
          lenstop = smallimage.btop - 1;
        } else if (overbottom(this.node)) {
          lenstop = smallimage.h + smallimage.btop - this.node.h - 1;
        }
        this.node.left = lensleft;
        this.node.top = lenstop;
        this.node.css({
          'left': lensleft + 'px',
          'top': lenstop + 'px'
        });
        if (settings.zoomType == 'reverse') {
          if ($.browser.msie && $.browser.version > 7) {
            $(this.node).empty().append(this.image);
          }
          $(this.image).css({
            position: 'absolute',
            display: 'block',
            left: -(this.node.left + 1 - smallimage.bleft) + 'px',
            top: -(this.node.top + 1 - smallimage.btop) + 'px'
          });
        }
        largeimage.setposition();
      };
      this.hide = function () {
        img.css({ 'opacity': 1 });
        this.node.hide();
      };
      this.show = function () {
        if (settings.zoomType != 'innerzoom' && (settings.lens || settings.zoomType == 'drag')) {
          this.node.show();
        }
        if (settings.zoomType == 'reverse') {
          img.css({ 'opacity': settings.imageOpacity });
        }
      };
      this.getoffset = function () {
        var o = {};
        o.left = $obj.node.left;
        o.top = $obj.node.top;
        return o;
      };
      return this;
    }
    ;
    function Stage() {
      var $obj = this;
      this.node = $('<div class=\'zoomWindow\'><div class=\'zoomWrapper\'><div class=\'zoomWrapperTitle\'></div><div class=\'zoomWrapperImage\'></div></div></div>');
      this.ieframe = $('<iframe class="zoomIframe" src="javascript:\'\';" marginwidth="0" marginheight="0" align="bottom" scrolling="no" frameborder="0" ></iframe>');
      this.setposition = function () {
        this.node.leftpos = 0;
        this.node.toppos = 0;
        if (settings.zoomType != 'innerzoom') {
          switch (settings.position) {
          case 'left':
            this.node.leftpos = smallimage.pos.l - smallimage.bleft - Math.abs(settings.xOffset) - settings.zoomWidth > 0 ? 0 - settings.zoomWidth - Math.abs(settings.xOffset) : smallimage.ow + Math.abs(settings.xOffset);
            this.node.toppos = Math.abs(settings.yOffset);
            break;
          case 'top':
            this.node.leftpos = Math.abs(settings.xOffset);
            this.node.toppos = smallimage.pos.t - smallimage.btop - Math.abs(settings.yOffset) - settings.zoomHeight > 0 ? 0 - settings.zoomHeight - Math.abs(settings.yOffset) : smallimage.oh + Math.abs(settings.yOffset);
            break;
          case 'bottom':
            this.node.leftpos = Math.abs(settings.xOffset);
            this.node.toppos = smallimage.pos.t - smallimage.btop + smallimage.oh + Math.abs(settings.yOffset) + settings.zoomHeight < screen.height ? smallimage.oh + Math.abs(settings.yOffset) : 0 - settings.zoomHeight - Math.abs(settings.yOffset);
            break;
          default:
            this.node.leftpos = smallimage.rightlimit + Math.abs(settings.xOffset) + settings.zoomWidth < screen.width ? smallimage.ow + Math.abs(settings.xOffset) : 0 - settings.zoomWidth - Math.abs(settings.xOffset);
            this.node.toppos = Math.abs(settings.yOffset);
            break;
          }
        }
        this.node.css({
          'left': this.node.leftpos + 'px',
          'top': this.node.toppos + 'px'
        });
        return this;
      };
      this.append = function () {
        $('.zoomPad', el).append(this.node);
        this.node.css({
          position: 'absolute',
          display: 'none',
          zIndex: 5001
        });
        if (settings.zoomType == 'innerzoom') {
          this.node.css({ cursor: 'default' });
          var thickness = smallimage.bleft == 0 ? 1 : smallimage.bleft;
          $('.zoomWrapper', this.node).css({ borderWidth: thickness + 'px' });
        }
        $('.zoomWrapper', this.node).css({
          width: Math.round(settings.zoomWidth) + 'px',
          borderWidth: thickness + 'px'
        });
        $('.zoomWrapperImage', this.node).css({
          width: '100%',
          height: Math.round(settings.zoomHeight) + 'px'
        });
        $('.zoomWrapperTitle', this.node).css({
          width: '100%',
          position: 'absolute'
        });
        $('.zoomWrapperTitle', this.node).hide();
        if (settings.title && zoomtitle.length > 0) {
          $('.zoomWrapperTitle', this.node).html(zoomtitle).show();
        }
        $obj.setposition();
      };
      this.hide = function () {
        switch (settings.hideEffect) {
        case 'fadeout':
          this.node.fadeOut(settings.fadeoutSpeed, function () {
          });
          break;
        default:
          this.node.hide();
          break;
        }
        this.ieframe.hide();
      };
      this.show = function () {
        switch (settings.showEffect) {
        case 'fadein':
          this.node.fadeIn();
          this.node.fadeIn(settings.fadeinSpeed, function () {
          });
          break;
        default:
          this.node.show();
          break;
        }
        if (isIE6 && settings.zoomType != 'innerzoom') {
          this.ieframe.width = this.node.width();
          this.ieframe.height = this.node.height();
          this.ieframe.left = this.node.leftpos;
          this.ieframe.top = this.node.toppos;
          this.ieframe.css({
            display: 'block',
            position: 'absolute',
            left: this.ieframe.left,
            top: this.ieframe.top,
            zIndex: 99,
            width: this.ieframe.width + 'px',
            height: this.ieframe.height + 'px'
          });
          $('.zoomPad', el).append(this.ieframe);
          this.ieframe.show();
        }
        ;
      };
    }
    ;
    function Largeimage() {
      var $obj = this;
      this.node = new Image();
      this.loadimage = function (url) {
        loader.show();
        this.url = url;
        this.node.style.position = 'absolute';
        this.node.style.border = '0px';
        this.node.style.display = 'none';
        this.node.style.left = '-5000px';
        this.node.style.top = '0px';
        document.body.appendChild(this.node);
        this.node.src = url;
      };
      this.fetchdata = function () {
        var image = $(this.node);
        var scale = {};
        this.node.style.display = 'block';
        $obj.w = image.width();
        $obj.h = image.height();
        $obj.pos = image.offset();
        $obj.pos.l = image.offset().left;
        $obj.pos.t = image.offset().top;
        $obj.pos.r = $obj.w + $obj.pos.l;
        $obj.pos.b = $obj.h + $obj.pos.t;
        scale.x = $obj.w / smallimage.w;
        scale.y = $obj.h / smallimage.h;
        el.scale = scale;
        document.body.removeChild(this.node);
        $('.zoomWrapperImage', el).empty().append(this.node);
        lens.setdimensions();
      };
      this.node.onerror = function () {
        alert('Problems while loading the big image.');
        throw 'Problems while loading the big image.';
      };
      this.node.onload = function () {
        $obj.fetchdata();
        loader.hide();
        el.largeimageloading = false;
        el.largeimageloaded = true;
        if (settings.zoomType == 'drag' || settings.alwaysOn) {
          lens.show();
          stage.show();
          lens.setcenter();
        }
      };
      this.setposition = function () {
        var left = -el.scale.x * (lens.getoffset().left - smallimage.bleft + 1);
        var top = -el.scale.y * (lens.getoffset().top - smallimage.btop + 1);
        $(this.node).css({
          'left': left + 'px',
          'top': top + 'px'
        });
      };
      return this;
    }
    ;
    $(el).data('jqzoom', obj);
  };
  $.jqzoom = {
    defaults: {
      zoomType: 'standard',
      zoomWidth: 300,
      zoomHeight: 300,
      xOffset: 10,
      yOffset: 0,
      position: 'right',
      preloadImages: true,
      preloadText: 'Loading zoom',
      title: true,
      lens: true,
      imageOpacity: 0.4,
      alwaysOn: false,
      showEffect: 'show',
      hideEffect: 'hide',
      fadeinSpeed: 'slow',
      fadeoutSpeed: '2000'
    },
    disable: function (el) {
      var api = $(el).data('jqzoom');
      api.disable();
      return false;
    },
    enable: function (el) {
      var api = $(el).data('jqzoom');
      api.enable();
      return false;
    },
    disableAll: function (el) {
      jqzoompluging_disabled = true;
    },
    enableAll: function (el) {
      jqzoompluging_disabled = false;
    }
  };
}(jQuery));
;
(function ($) {
  function maybeCall(thing, ctx) {
    return typeof thing == 'function' ? thing.call(ctx) : thing;
  }
  ;
  function Tipsy(element, options) {
    this.$element = $(element);
    this.options = options;
    this.enabled = true;
    this.fixTitle();
  }
  ;
  Tipsy.prototype = {
    show: function () {
      var title = this.getTitle();
      if (title && this.enabled) {
        var $tip = this.tip();
        $tip.find('.tipsy-inner')[this.options.html ? 'html' : 'text'](title);
        $tip[0].className = 'tipsy';
        $tip.remove().css({
          top: 0,
          left: 0,
          visibility: 'hidden',
          display: 'block'
        }).prependTo(document.body);
        var pos = $.extend({}, this.$element.offset(), {
            width: this.$element[0].offsetWidth,
            height: this.$element[0].offsetHeight
          });
        var actualWidth = $tip[0].offsetWidth, actualHeight = $tip[0].offsetHeight, gravity = maybeCall(this.options.gravity, this.$element[0]);
        var tp;
        switch (gravity.charAt(0)) {
        case 'n':
          tp = {
            top: pos.top + pos.height + this.options.offset,
            left: pos.left + pos.width / 2 - actualWidth / 2
          };
          break;
        case 's':
          tp = {
            top: pos.top - actualHeight - this.options.offset,
            left: pos.left + pos.width / 2 - actualWidth / 2
          };
          break;
        case 'e':
          tp = {
            top: pos.top + pos.height / 2 - actualHeight / 2,
            left: pos.left - actualWidth - this.options.offset
          };
          break;
        case 'w':
          tp = {
            top: pos.top + pos.height / 2 - actualHeight / 2,
            left: pos.left + pos.width + this.options.offset
          };
          break;
        }
        if (gravity.length == 2) {
          if (gravity.charAt(1) == 'w') {
            tp.left = pos.left + pos.width / 2 - 15;
          } else {
            tp.left = pos.left + pos.width / 2 - actualWidth + 15;
          }
        }
        $tip.css(tp).addClass('tipsy-' + gravity);
        $tip.find('.tipsy-arrow')[0].className = 'tipsy-arrow tipsy-arrow-' + gravity.charAt(0);
        if (this.options.className) {
          $tip.addClass(maybeCall(this.options.className, this.$element[0]));
        }
        if (this.options.fade) {
          $tip.stop().css({
            opacity: 0,
            display: 'block',
            visibility: 'visible'
          }).animate({ opacity: this.options.opacity });
        } else {
          $tip.css({
            visibility: 'visible',
            opacity: this.options.opacity
          });
        }
      }
    },
    hide: function () {
      if (this.options.fade) {
        this.tip().stop().fadeOut(function () {
          $(this).remove();
        });
      } else {
        this.tip().remove();
      }
    },
    fixTitle: function () {
      var $e = this.$element;
      if ($e.attr('title') || typeof $e.attr('original-title') != 'string') {
        $e.attr('original-title', $e.attr('title') || '').removeAttr('title');
      }
    },
    getTitle: function () {
      var title, $e = this.$element, o = this.options;
      this.fixTitle();
      var title, o = this.options;
      if (typeof o.title == 'string') {
        title = $e.attr(o.title == 'title' ? 'original-title' : o.title);
      } else if (typeof o.title == 'function') {
        title = o.title.call($e[0]);
      }
      title = ('' + title).replace(/(^\s*|\s*$)/, '');
      return title || o.fallback;
    },
    tip: function () {
      if (!this.$tip) {
        this.$tip = $('<div class="tipsy"></div>').html('<div class="tipsy-arrow"></div><div class="tipsy-inner"></div>');
      }
      return this.$tip;
    },
    validate: function () {
      if (!this.$element[0].parentNode) {
        this.hide();
        this.$element = null;
        this.options = null;
      }
    },
    enable: function () {
      this.enabled = true;
    },
    disable: function () {
      this.enabled = false;
    },
    toggleEnabled: function () {
      this.enabled = !this.enabled;
    }
  };
  $.fn.tipsy = function (options) {
    if (options === true) {
      return this.data('tipsy');
    } else if (typeof options == 'string') {
      var tipsy = this.data('tipsy');
      if (tipsy)
        tipsy[options]();
      return this;
    }
    options = $.extend({}, $.fn.tipsy.defaults, options);
    function get(ele) {
      var tipsy = $.data(ele, 'tipsy');
      if (!tipsy) {
        tipsy = new Tipsy(ele, $.fn.tipsy.elementOptions(ele, options));
        $.data(ele, 'tipsy', tipsy);
      }
      return tipsy;
    }
    function enter() {
      var tipsy = get(this);
      tipsy.hoverState = 'in';
      if (options.delayIn == 0) {
        tipsy.show();
      } else {
        tipsy.fixTitle();
        setTimeout(function () {
          if (tipsy.hoverState == 'in')
            tipsy.show();
        }, options.delayIn);
      }
    }
    ;
    function leave() {
      var tipsy = get(this);
      tipsy.hoverState = 'out';
      if (options.delayOut == 0) {
        tipsy.hide();
      } else {
        setTimeout(function () {
          if (tipsy.hoverState == 'out')
            tipsy.hide();
        }, options.delayOut);
      }
    }
    ;
    if (!options.live)
      this.each(function () {
        get(this);
      });
    if (options.trigger != 'manual') {
      var binder = options.live ? 'live' : 'bind', eventIn = options.trigger == 'hover' ? 'mouseenter' : 'focus', eventOut = options.trigger == 'hover' ? 'mouseleave' : 'blur';
      this[binder](eventIn, enter)[binder](eventOut, leave);
    }
    return this;
  };
  $.fn.tipsy.defaults = {
    className: null,
    delayIn: 0,
    delayOut: 0,
    fade: false,
    fallback: '',
    gravity: 'n',
    html: false,
    live: false,
    offset: 0,
    opacity: 0.8,
    title: 'title',
    trigger: 'hover'
  };
  $.fn.tipsy.elementOptions = function (ele, options) {
    return $.metadata ? $.extend({}, options, $(ele).metadata()) : options;
  };
  $.fn.tipsy.autoNS = function () {
    return $(this).offset().top > $(document).scrollTop() + $(window).height() / 2 ? 's' : 'n';
  };
  $.fn.tipsy.autoWE = function () {
    return $(this).offset().left > $(document).scrollLeft() + $(window).width() / 2 ? 'e' : 'w';
  };
  $.fn.tipsy.autoBounds = function (margin, prefer) {
    return function () {
      var dir = {
          ns: prefer[0],
          ew: prefer.length > 1 ? prefer[1] : false
        }, boundTop = $(document).scrollTop() + margin, boundLeft = $(document).scrollLeft() + margin, $this = $(this);
      if ($this.offset().top < boundTop)
        dir.ns = 'n';
      if ($this.offset().left < boundLeft)
        dir.ew = 'w';
      if ($(window).width() + $(document).scrollLeft() - $this.offset().left < margin)
        dir.ew = 'e';
      if ($(window).height() + $(document).scrollTop() - $this.offset().top < margin)
        dir.ns = 's';
      return dir.ns + (dir.ew ? dir.ew : '');
    };
  };
}(jQuery));
;
(function ($, undefined) {
  var lastActive, startXPos, startYPos, clickDragged, baseClasses = 'ui-button ui-widget ui-state-default ui-corner-all', typeClasses = 'ui-button-icons-only ui-button-icon-only ui-button-text-icons ui-button-text-icon-primary ui-button-text-icon-secondary ui-button-text-only', formResetHandler = function () {
      var form = $(this);
      setTimeout(function () {
        form.find(':ui-button').button('refresh');
      }, 1);
    }, radioGroup = function (radio) {
      var name = radio.name, form = radio.form, radios = $([]);
      if (name) {
        name = name.replace(/'/g, '\\\'');
        if (form) {
          radios = $(form).find('[name=\'' + name + '\']');
        } else {
          radios = $('[name=\'' + name + '\']', radio.ownerDocument).filter(function () {
            return !this.form;
          });
        }
      }
      return radios;
    };
  $.widget('ui.button', {
    version: '@VERSION',
    defaultElement: '<button>',
    options: {
      disabled: null,
      text: true,
      label: null,
      icons: {
        primary: null,
        secondary: null
      }
    },
    _create: function () {
      this.element.closest('form').unbind('reset' + this.eventNamespace).bind('reset' + this.eventNamespace, formResetHandler);
      if (typeof this.options.disabled !== 'boolean') {
        this.options.disabled = !!this.element.prop('disabled');
      } else {
        this.element.prop('disabled', this.options.disabled);
      }
      this._determineButtonType();
      this.hasTitle = !!this.buttonElement.attr('title');
      var that = this, options = this.options, toggleButton = this.type === 'checkbox' || this.type === 'radio', activeClass = !toggleButton ? 'ui-state-active' : '';
      if (options.label === null) {
        options.label = this.type === 'input' ? this.buttonElement.val() : this.buttonElement.html();
      }
      this._hoverable(this.buttonElement);
      this.buttonElement.addClass(baseClasses).attr('role', 'button').bind('mouseenter' + this.eventNamespace, function () {
        if (options.disabled) {
          return;
        }
        if (this === lastActive) {
          $(this).addClass('ui-state-active');
        }
      }).bind('mouseleave' + this.eventNamespace, function () {
        if (options.disabled) {
          return;
        }
        $(this).removeClass(activeClass);
      }).bind('click' + this.eventNamespace, function (event) {
        if (options.disabled) {
          event.preventDefault();
          event.stopImmediatePropagation();
        }
      });
      this._on({
        focus: function () {
          this.buttonElement.addClass('ui-state-focus');
        },
        blur: function () {
          this.buttonElement.removeClass('ui-state-focus');
        }
      });
      if (toggleButton) {
        this.element.bind('change' + this.eventNamespace, function () {
          if (clickDragged) {
            return;
          }
          that.refresh();
        });
        this.buttonElement.bind('mousedown' + this.eventNamespace, function (event) {
          if (options.disabled) {
            return;
          }
          clickDragged = false;
          startXPos = event.pageX;
          startYPos = event.pageY;
        }).bind('mouseup' + this.eventNamespace, function (event) {
          if (options.disabled) {
            return;
          }
          if (startXPos !== event.pageX || startYPos !== event.pageY) {
            clickDragged = true;
          }
        });
      }
      if (this.type === 'checkbox') {
        this.buttonElement.bind('click' + this.eventNamespace, function () {
          if (options.disabled || clickDragged) {
            return false;
          }
        });
      } else if (this.type === 'radio') {
        this.buttonElement.bind('click' + this.eventNamespace, function () {
          if (options.disabled || clickDragged) {
            return false;
          }
          $(this).addClass('ui-state-active');
          that.buttonElement.attr('aria-pressed', 'true');
          var radio = that.element[0];
          radioGroup(radio).not(radio).map(function () {
            return $(this).button('widget')[0];
          }).removeClass('ui-state-active').attr('aria-pressed', 'false');
        });
      } else {
        this.buttonElement.bind('mousedown' + this.eventNamespace, function () {
          if (options.disabled) {
            return false;
          }
          $(this).addClass('ui-state-active');
          lastActive = this;
          that.document.one('mouseup', function () {
            lastActive = null;
          });
        }).bind('mouseup' + this.eventNamespace, function () {
          if (options.disabled) {
            return false;
          }
          $(this).removeClass('ui-state-active');
        }).bind('keydown' + this.eventNamespace, function (event) {
          if (options.disabled) {
            return false;
          }
          if (event.keyCode === $.ui.keyCode.SPACE || event.keyCode === $.ui.keyCode.ENTER) {
            $(this).addClass('ui-state-active');
          }
        }).bind('keyup' + this.eventNamespace + ' blur' + this.eventNamespace, function () {
          $(this).removeClass('ui-state-active');
        });
        if (this.buttonElement.is('a')) {
          this.buttonElement.keyup(function (event) {
            if (event.keyCode === $.ui.keyCode.SPACE) {
              $(this).click();
            }
          });
        }
      }
      this._setOption('disabled', options.disabled);
      this._resetButton();
    },
    _determineButtonType: function () {
      var ancestor, labelSelector, checked;
      if (this.element.is('[type=checkbox]')) {
        this.type = 'checkbox';
      } else if (this.element.is('[type=radio]')) {
        this.type = 'radio';
      } else if (this.element.is('input')) {
        this.type = 'input';
      } else {
        this.type = 'button';
      }
      if (this.type === 'checkbox' || this.type === 'radio') {
        ancestor = this.element.parents().last();
        labelSelector = 'label[for=\'' + this.element.attr('id') + '\']';
        this.buttonElement = ancestor.find(labelSelector);
        if (!this.buttonElement.length) {
          ancestor = ancestor.length ? ancestor.siblings() : this.element.siblings();
          this.buttonElement = ancestor.filter(labelSelector);
          if (!this.buttonElement.length) {
            this.buttonElement = ancestor.find(labelSelector);
          }
        }
        this.element.addClass('ui-helper-hidden-accessible');
        checked = this.element.is(':checked');
        if (checked) {
          this.buttonElement.addClass('ui-state-active');
        }
        this.buttonElement.prop('aria-pressed', checked);
      } else {
        this.buttonElement = this.element;
      }
    },
    widget: function () {
      return this.buttonElement;
    },
    _destroy: function () {
      this.element.removeClass('ui-helper-hidden-accessible');
      this.buttonElement.removeClass(baseClasses + ' ui-state-active ' + typeClasses).removeAttr('role').removeAttr('aria-pressed').html(this.buttonElement.find('.ui-button-text').html());
      if (!this.hasTitle) {
        this.buttonElement.removeAttr('title');
      }
    },
    _setOption: function (key, value) {
      this._super(key, value);
      if (key === 'disabled') {
        this.widget().toggleClass('ui-state-disabled', !!value);
        this.element.prop('disabled', !!value);
        if (value) {
          this.buttonElement.removeClass('ui-state-focus');
        }
        return;
      }
      this._resetButton();
    },
    refresh: function () {
      var isDisabled = this.element.is('input, button') ? this.element.is(':disabled') : this.element.hasClass('ui-button-disabled');
      if (isDisabled !== this.options.disabled) {
        this._setOption('disabled', isDisabled);
      }
      if (this.type === 'radio') {
        radioGroup(this.element[0]).each(function () {
          if ($(this).is(':checked')) {
            $(this).button('widget').addClass('ui-state-active').attr('aria-pressed', 'true');
          } else {
            $(this).button('widget').removeClass('ui-state-active').attr('aria-pressed', 'false');
          }
        });
      } else if (this.type === 'checkbox') {
        if (this.element.is(':checked')) {
          this.buttonElement.addClass('ui-state-active').attr('aria-pressed', 'true');
        } else {
          this.buttonElement.removeClass('ui-state-active').attr('aria-pressed', 'false');
        }
      }
    },
    _resetButton: function () {
      if (this.type === 'input') {
        if (this.options.label) {
          this.element.val(this.options.label);
        }
        return;
      }
      var buttonElement = this.buttonElement.removeClass(typeClasses), buttonText = $('<span></span>', this.document[0]).addClass('ui-button-text').html(this.options.label).appendTo(buttonElement.empty()).text(), icons = this.options.icons, multipleIcons = icons.primary && icons.secondary, buttonClasses = [];
      if (icons.primary || icons.secondary) {
        if (this.options.text) {
          buttonClasses.push('ui-button-text-icon' + (multipleIcons ? 's' : icons.primary ? '-primary' : '-secondary'));
        }
        if (icons.primary) {
          buttonElement.prepend('<span class=\'ui-button-icon-primary ui-icon ' + icons.primary + '\'></span>');
        }
        if (icons.secondary) {
          buttonElement.append('<span class=\'ui-button-icon-secondary ui-icon ' + icons.secondary + '\'></span>');
        }
        if (!this.options.text) {
          buttonClasses.push(multipleIcons ? 'ui-button-icons-only' : 'ui-button-icon-only');
          if (!this.hasTitle) {
            buttonElement.attr('title', $.trim(buttonText));
          }
        }
      } else {
        buttonClasses.push('ui-button-text-only');
      }
      buttonElement.addClass(buttonClasses.join(' '));
    }
  });
  $.widget('ui.buttonset', {
    version: '@VERSION',
    options: { items: 'button, input[type=button], input[type=submit], input[type=reset], input[type=checkbox], input[type=radio], a, :data(ui-button)' },
    _create: function () {
      this.element.addClass('ui-buttonset');
    },
    _init: function () {
      this.refresh();
    },
    _setOption: function (key, value) {
      if (key === 'disabled') {
        this.buttons.button('option', key, value);
      }
      this._super(key, value);
    },
    refresh: function () {
      var rtl = this.element.css('direction') === 'rtl';
      this.buttons = this.element.find(this.options.items).filter(':ui-button').button('refresh').end().not(':ui-button').button().end().map(function () {
        return $(this).button('widget')[0];
      }).removeClass('ui-corner-all ui-corner-left ui-corner-right').filter(':first').addClass(rtl ? 'ui-corner-right' : 'ui-corner-left').end().filter(':last').addClass(rtl ? 'ui-corner-left' : 'ui-corner-right').end().end();
    },
    _destroy: function () {
      this.element.removeClass('ui-buttonset');
      this.buttons.map(function () {
        return $(this).button('widget')[0];
      }).removeClass('ui-corner-left ui-corner-right').end().button('destroy');
    }
  });
}(jQuery));
;
(function ($) {
  $.fn.uppydowner = function (settings) {
    var upDownStep = 1;
    var config = {
        classModifier: 'uppydowner',
        upText: 'One product more',
        downText: 'One product less',
        upButton: '+',
        upPlaceBefore: false,
        downButton: '&ndash;',
        downPlaceBefore: true,
        step: 1,
        minValue: -10000,
        maxValue: 10000
      };
    if (settings) {
      $.extend(config, settings);
    }
    base = $('span[id*="' + config.classModifier + 'button"]').length / 2;
    this.each(function (index) {
      $This = $(this);
      upDownStep = parseFloat(config.step);
      var down = '<span role="button" title="' + config.downText + '" ' + 'id="' + config.classModifier + 'button' + (base + index) + '_down" ' + 'class="' + config.classModifier + 'button down">' + config.downButton + '</span>';
      var up = '<span role="button" title="' + config.upText + '" ' + 'id="' + config.classModifier + 'button' + (base + index) + '_up" ' + 'class="' + config.classModifier + 'button up">' + config.upButton + '</span>';
      if (config.downPlaceBefore) {
        $This.before(down);
      } else {
        $This.after(down);
      }
      if (config.upPlaceBefore) {
        $This.before(up);
      } else {
        $This.after(up);
      }
      $('#' + config.classModifier + 'button' + (base + index) + '_down').click(function () {
        var theInput = parseFloat($(this).siblings('input').val());
        if (theInput > config.minValue) {
          $(this).siblings('input').val(theInput - upDownStep).trigger('change');
        }
      });
      $('#' + config.classModifier + 'button' + (base + index) + '_up').click(function () {
        var theInput = parseFloat($(this).siblings('input').val());
        if (theInput < config.maxValue) {
          $(this).siblings('input').val(theInput + upDownStep).trigger('change');
        }
      });
    });
  };
}(jQuery));
;
'use strict';
angular.module('kawwa2').directive('fieldComment', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      var json = jQuery.extend({
          gravity: 'w',
          fade: true,
          trigger: 'click'
        }, scope.$eval(attrs.fieldComment));
      jQuery(element).tipsy(json);
    }
  };
});
;
'use strict';
angular.module('kawwa2').directive('kawwaHeader', function () {
  console.log('test');
  return {
    template: '<head ng-transclude>\n    <meta charset="utf-8">\n    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">\n    <title></title>\n    <meta name="viewport" content="width=device-width">\n    <link type="text/css" rel="stylesheet" href="theme/css/k-theme0.css"/>\n</head>',
    transclude: true,
    replace: true,
    restrict: 'A'
  };
});
;
'use strict';
angular.module('kawwa2').run([
  '$templateCache',
  function ($templateCache) {
    $templateCache.put('ProductGallery', '<div class="k-product-gallery photo-data">\n    <p>\n        <a class="jqzoom" rel="{{title}}" href="{{gallery[0].hd}}" title="{{gallery[0].title}}">\n            <img class="photo" ng-src="{{gallery[0].small}}" alt="{{gallery[0].title}}"/>\n        </a>\n    </p>\n    <ul class="thumblist">    \n        <li ng-repeat="image in gallery" ng-class="{zoomThumbActive : $index==0}">\n            <a  href="#" rel="{gallery: \'{{title}}\', smallimage:\'{{image.small}}\',largeimage:\'{{image.hd}}\'}">\n            <img ng-src="{{image.thumb}}" alt="{{image.title}}"/></a></li>\n    </ul>\n</div>\n\n\n');
  }
]);
angular.module('kawwa2').directive('productGallery', [
  '$templateCache',
  '$timeout',
  function ($templateCache, $timeout) {
    return {
      restrict: 'A',
      template: $templateCache.get('ProductGallery'),
      replace: true,
      scope: {
        options: '=',
        title: '@',
        gallery: '='
      },
      link: function (scope, element) {
        if (!scope.title) {
          scope.title = 'gal1';
        }
        if (!scope.options) {
          scope.options = {};
        }
        scope.options = jQuery.extend({
          zoomType: 'standard',
          lens: true,
          preloadImages: true,
          alwaysOn: false
        }, scope.options);
        $timeout(function () {
          if (jQuery.fn.jqzoom) {
            element.children(0).children(0).jqzoom(scope.options);
          }
        }, 0);
      }
    };
  }
]);
;
'use strict';
angular.module('kawwa2').run([
  '$templateCache',
  function ($templateCache) {
    $templateCache.put('ProductOptions', '<p>\n    <!-- directive: ng-repeat product in products -->\n    <input data-ng-model="selected" type="radio" id="{{product.name}}" value="{{product.name}}" name="{{name}}" checked="{checked : $index==0}"/>\n    <label for="{{product.name}}"><img ng-src="{{product.img}}" alt="{{product.alt}}"/></label>\n    <!-- /ng-repeat -->\n</p>\n       \n\n');
  }
]);
angular.module('kawwa2').directive('productOptions', [
  '$templateCache',
  function ($templateCache) {
    return {
      restrict: 'A',
      template: $templateCache.get('ProductOptions'),
      replace: true,
      scope: {
        name: '@',
        products: '='
      },
      link: function (scope, element, attrs) {
        var json = jQuery.extend({}, scope.$eval(attrs.productOptions));
        jQuery(element).buttonset(json);
      }
    };
  }
]);
;
'use strict';
function incrementTest() {
  var i = document.createElement('input');
  i.setAttribute('type', 'number');
  return i.type === 'text';
}
angular.module('kawwa2').directive('productQuantity', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      var json = jQuery.extend({}, scope.$eval(attrs.productQuantity));
      if (incrementTest()) {
        jQuery(element).addClass('uppydowner');
        jQuery(element).uppydowner(json);
      } else {
        jQuery(element).css('width', '3em');
      }
    }
  };
});
;
'use strict';
function putObject(path, object, value) {
  var modelPath = path.split('.');
  function fill(object, elements, depth, value) {
    var hasNext = depth + 1 < elements.length;
    if (depth < elements.length && hasNext) {
      if (!object.hasOwnProperty(modelPath[depth])) {
        object[modelPath[depth]] = {};
      }
      fill(object[modelPath[depth]], elements, ++depth, value);
    } else {
      object[modelPath[depth]] = value;
    }
  }
  fill(object, modelPath, 0, value);
}
angular.module('kawwa2').directive('raty', function () {
  return {
    restrict: 'A',
    link: function (scope, element, attrs) {
      var json = jQuery.extend({
          callback: function (value) {
            putObject(attrs.ngModel, scope, value);
            if (!scope.$$phase)
              scope.$apply();
          }
        }, scope.$eval(attrs.ratingOptions));
      jQuery(element).children('input').rating(json);
      scope.$watch(attrs.ngModel, function (value) {
        jQuery(element).children('input').rating('select', '' + value);
      }, true);
    }
  };
});