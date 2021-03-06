angular-chart.css                                                                                   000644  000000  000000  00000001313 12524077307 012473  0                                                                                                    ustar 00                                                                000000  000000                                                                                                                                                                         .chart-legend,.bar-legend,.line-legend,.pie-legend,.radar-legend,.polararea-legend,.doughnut-legend{list-style-type:none;margin-top:5px;text-align:center}.chart-legend li,.bar-legend li,.line-legend li,.pie-legend li,.radar-legend li,.polararea-legend li,.doughnut-legend li{display:inline-block;white-space:nowrap;position:relative;margin-bottom:4px;border-radius:5px;padding:2px 8px 2px 28px;font-size:smaller;cursor:default}.chart-legend li span,.bar-legend li span,.line-legend li span,.pie-legend li span,.radar-legend li span,.polararea-legend li span,.doughnut-legend li span{display:block;position:absolute;left:0;top:0;width:20px;height:20px;border-radius:5px}
/*# sourceMappingURL=angular-chart.css.map */                                                                                                                                                                                                                                                                                                                     angular-chart.css.map                                                                               000644  000000  000000  00000002170 12524077307 013251  0                                                                                                    ustar 00                                                                000000  000000                                                                                                                                                                         {"version":3,"sources":["angular-chart.less"],"names":[],"mappings":"AAAA;AAAe;AAAa;AAAc;AAAa;AAAe;AAAmB;EACvF,qBAAA;EACA,eAAA;EACA,kBAAA;;AAHF,aAKE;AALa,WAKb;AAL0B,YAK1B;AALwC,WAKxC;AALqD,aAKrD;AALoE,iBAKpE;AALuF,gBAKvF;EACE,qBAAA;EACA,mBAAA;EACA,kBAAA;EACA,kBAAA;EACA,kBAAA;EACA,yBAAA;EACA,kBAAA;EACA,eAAA;;AAbJ,aAKE,GAUE;AAfW,WAKb,GAUE;AAfwB,YAK1B,GAUE;AAfsC,WAKxC,GAUE;AAfmD,aAKrD,GAUE;AAfkE,iBAKpE,GAUE;AAfqF,gBAKvF,GAUE;EACE,cAAA;EACA,kBAAA;EACA,OAAA;EACA,MAAA;EACA,WAAA;EACA,YAAA;EACA,kBAAA","file":"angular-chart.css","sourcesContent":[".chart-legend, .bar-legend, .line-legend, .pie-legend, .radar-legend, .polararea-legend, .doughnut-legend {\n  list-style-type: none;\n  margin-top: 5px;\n  text-align: center;\n\n  li {\n    display: inline-block;\n    white-space: nowrap;\n    position: relative;\n    margin-bottom: 4px;\n    border-radius: 5px;\n    padding: 2px 8px 2px 28px;\n    font-size: smaller;\n    cursor: default;\n\n    span {\n      display: block;\n      position: absolute;\n      left: 0;\n      top: 0;\n      width: 20px;\n      height: 20px;\n      border-radius: 5px;\n    }\n  }\n}"],"sourceRoot":"/source/"}                                                                                                                                                                                                                                                                                                                                                                                                        angular-chart.js                                                                                    000644  000000  000000  00000023671 12524077307 012332  0                                                                                                    ustar 00                                                                000000  000000                                                                                                                                                                         (function (factory) {
  'use strict';
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['angular', 'chart.js'], factory);
  } else if (typeof exports === 'object') {
    // Node/CommonJS
    module.exports = factory(require('angular'), require('chart.js'));
  } else {
    // Browser globals
    factory(angular, Chart);
  }
}(function (angular, Chart) {
  'use strict';

  Chart.defaults.global.responsive = true;
  Chart.defaults.global.multiTooltipTemplate = '<%if (datasetLabel){%><%=datasetLabel%>: <%}%><%= value %>';

  Chart.defaults.global.colours = [
    '#97BBCD', // blue
    '#DCDCDC', // light grey
    '#F7464A', // red
    '#46BFBD', // green
    '#FDB45C', // yellow
    '#949FB1', // grey
    '#4D5360'  // dark grey
  ];

  angular.module('chart.js', [])
    .provider('ChartJs', ChartJsProvider)
    .factory('ChartJsFactory', ['ChartJs', ChartJsFactory])
    .directive('chartBase', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory(); }])
    .directive('chartLine', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Line'); }])
    .directive('chartBar', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Bar'); }])
    .directive('chartRadar', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Radar'); }])
    .directive('chartDoughnut', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Doughnut'); }])
    .directive('chartPie', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Pie'); }])
    .directive('chartPolarArea', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('PolarArea'); }]);

  /**
   * Wrapper for chart.js
   * Allows configuring chart js using the provider
   *
   * angular.module('myModule', ['chart.js']).config(function(ChartJsProvider) {
   *   ChartJsProvider.setOptions({ responsive: true });
   *   ChartJsProvider.setOptions('Line', { responsive: false });
   * })))
   */
  function ChartJsProvider () {
    var options = {};
    var ChartJs = {
      Chart: Chart,
      getOptions: function (type) {
        var typeOptions = type && options[type] || {};
        return angular.extend({}, options, typeOptions);
      }
    };

    /**
     * Allow to set global options during configuration
     */
    this.setOptions = function (type, customOptions) {
      // If no type was specified set option for the global object
      if (! customOptions) {
        customOptions = type;
        options = angular.extend(options, customOptions);
        return;
      }
      // Set options for the specific chart
      options[type] = angular.extend(options[type] || {}, customOptions);
    };

    this.$get = function () {
      return ChartJs;
    };
  }

  function ChartJsFactory (ChartJs) {
    return function chart (type) {
      return {
        restrict: 'CA',
        scope: {
          data: '=',
          labels: '=',
          options: '=',
          series: '=',
          colours: '=?',
          getColour: '=?',
          chartType: '=',
          legend: '@',
          click: '=',
          hover: '='
        },
        link: function (scope, elem/*, attrs */) {
          var chart, container = document.createElement('div');
          container.className = 'chart-container';
          elem.replaceWith(container);
          container.appendChild(elem[0]);

          if (typeof window.G_vmlCanvasManager === 'object' && window.G_vmlCanvasManager !== null) {
            if (typeof window.G_vmlCanvasManager.initElement === 'function') {
              window.G_vmlCanvasManager.initElement(elem[0]);
            }
          }

          // Order of setting "watch" matter

          scope.$watch('data', function (newVal, oldVal) {
            if (! newVal || ! newVal.length || (Array.isArray(newVal[0]) && ! newVal[0].length)) return;
            var chartType = type || scope.chartType;
            if (! chartType) return;

            if (chart) {
              if (canUpdateChart(newVal, oldVal)) return updateChart(chart, newVal, scope);
              chart.destroy();
            }

            chart = createChart(chartType, scope, elem);
          }, true);

          scope.$watch('series', resetChart, true);
          scope.$watch('labels', resetChart, true);
          scope.$watch('options', resetChart, true);
          scope.$watch('colours', resetChart, true);

          scope.$watch('chartType', function (newVal, oldVal) {
            if (isEmpty(newVal)) return;
            if (angular.equals(newVal, oldVal)) return;
            if (chart) chart.destroy();
            chart = createChart(newVal, scope, elem);
          });

          scope.$on('$destroy', function () {
            if (chart) chart.destroy();
          });

          function resetChart (newVal, oldVal) {
            if (isEmpty(newVal)) return;
            if (angular.equals(newVal, oldVal)) return;
            var chartType = type || scope.chartType;
            if (! chartType) return;

            // chart.update() doesn't work for series and labels
            // so we have to re-create the chart entirely
            if (chart) chart.destroy();

            chart = createChart(chartType, scope, elem);
          }
        }
      };
    };

    function canUpdateChart (newVal, oldVal) {
      if (newVal && oldVal && newVal.length && oldVal.length) {
        return Array.isArray(newVal[0]) ?
        newVal.length === oldVal.length && newVal[0].length === oldVal[0].length :
          oldVal.reduce(sum, 0) > 0 ? newVal.length === oldVal.length : false;
      }
      return false;
    }

    function sum (carry, val) {
      return carry + val;
    }

    function createChart (type, scope, elem) {
      if (! scope.data || ! scope.data.length) return;
      scope.getColour = typeof scope.getColour === 'function' ? scope.getColour : getRandomColour;
      scope.colours = getColours(type, scope);
      var cvs = elem[0], ctx = cvs.getContext('2d');
      var data = Array.isArray(scope.data[0]) ?
        getDataSets(scope.labels, scope.data, scope.series || [], scope.colours) :
        getData(scope.labels, scope.data, scope.colours);
      var options = angular.extend({}, ChartJs.getOptions(type), scope.options);
      var chart = new ChartJs.Chart(ctx)[type](data, options);
      scope.$emit('create', chart);

      ['hover', 'click'].forEach(function (action) {
        if (scope[action]) cvs[action === 'click' ? 'onclick' : 'onmousemove'] = getEventHandler(scope, chart, action);
      });
      if (scope.legend && scope.legend !== 'false') setLegend(elem, chart);
      return chart;
    }

    function getEventHandler (scope, chart, action) {
      return function (evt) {
        var atEvent = chart.getPointsAtEvent || chart.getBarsAtEvent || chart.getSegmentsAtEvent;
        if (atEvent) {
          var activePoints = atEvent.call(chart, evt);
          scope[action](activePoints, evt);
          scope.$apply();
        }
      };
    }

    function getColours (type, scope) {
      var colours = angular.copy(scope.colours ||
        ChartJs.getOptions(type).colours ||
        Chart.defaults.global.colours
      );
      while (colours.length < scope.data.length) {
        colours.push(scope.getColour());
      }
      return colours.map(convertColour);
    }

    function convertColour (colour) {
      if (typeof colour === 'object' && colour !== null) return colour;
      if (typeof colour === 'string' && colour[0] === '#') return getColour(hexToRgb(colour.substr(1)));
      return getRandomColour();
    }

    function getRandomColour () {
      var colour = [getRandomInt(0, 255), getRandomInt(0, 255), getRandomInt(0, 255)];
      return getColour(colour);
    }

    function getColour (colour) {
      return {
        fillColor: rgba(colour, 0.2),
        strokeColor: rgba(colour, 1),
        pointColor: rgba(colour, 1),
        pointStrokeColor: '#fff',
        pointHighlightFill: '#fff',
        pointHighlightStroke: rgba(colour, 0.8)
      };
    }

    function getRandomInt (min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    function rgba (colour, alpha) {
      return 'rgba(' + colour.concat(alpha).join(',') + ')';
    }

    // Credit: http://stackoverflow.com/a/11508164/1190235
    function hexToRgb (hex) {
      var bigint = parseInt(hex, 16),
        r = (bigint >> 16) & 255,
        g = (bigint >> 8) & 255,
        b = bigint & 255;

      return [r, g, b];
    }

    function getDataSets (labels, data, series, colours) {
      return {
        labels: labels,
        datasets: data.map(function (item, i) {
          var dataSet = angular.copy(colours[i]);
          dataSet.label = series[i];
          dataSet.data = item;
          return dataSet;
        })
      };
    }

    function getData (labels, data, colours) {
      return labels.map(function (label, i) {
        return {
          label: label,
          value: data[i],
          color: colours[i].strokeColor,
          highlight: colours[i].pointHighlightStroke
        };
      });
    }

    function setLegend (elem, chart) {
      var $parent = elem.parent(),
          $oldLegend = $parent.find('chart-legend'),
          legend = '<chart-legend>' + chart.generateLegend() + '</chart-legend>';
      if ($oldLegend.length) $oldLegend.replaceWith(legend);
      else $parent.append(legend);
    }

    function updateChart (chart, values, scope) {
      if (Array.isArray(scope.data[0])) {
        chart.datasets.forEach(function (dataset, i) {
          (dataset.points || dataset.bars).forEach(function (dataItem, j) {
            dataItem.value = values[i][j];
          });
        });
      } else {
        chart.segments.forEach(function (segment, i) {
          segment.value = values[i];
        });
      }
      chart.update();
      scope.$emit('update', chart);
    }

    function isEmpty (value) {
      return ! value ||
        (Array.isArray(value) && ! value.length) ||
        (typeof value === 'object' && ! Object.keys(value).length);
    }

  }
}));
                                                                       angular-chart.min.js                                                                                000644  000000  000000  00000010676 12524077307 013115  0                                                                                                    ustar 00                                                                000000  000000                                                                                                                                                                         !function(t){"use strict";"function"==typeof define&&define.amd?define(["angular","chart.js"],t):"object"==typeof exports?module.exports=t(require("angular"),require("chart.js")):t(angular,Chart)}(function(t,e){"use strict";function n(){var n={},r={Chart:e,getOptions:function(e){var r=e&&n[e]||{};return t.extend({},n,r)}};this.setOptions=function(e,r){return r?(n[e]=t.extend(n[e]||{},r),void 0):(r=e,n=t.extend(n,r),void 0)},this.$get=function(){return r}}function r(n){function r(t,e){return t&&e&&t.length&&e.length?Array.isArray(t[0])?t.length===e.length&&t[0].length===e[0].length:e.reduce(a,0)>0?t.length===e.length:!1:!1}function a(t,e){return t+e}function o(e,r,a){if(r.data&&r.data.length){r.getColour="function"==typeof r.getColour?r.getColour:l,r.colours=c(e,r);var o=a[0],u=o.getContext("2d"),s=Array.isArray(r.data[0])?g(r.labels,r.data,r.series||[],r.colours):p(r.labels,r.data,r.colours),f=t.extend({},n.getOptions(e),r.options),h=new n.Chart(u)[e](s,f);return r.$emit("create",h),["hover","click"].forEach(function(t){r[t]&&(o["click"===t?"onclick":"onmousemove"]=i(r,h,t))}),r.legend&&"false"!==r.legend&&v(a,h),h}}function i(t,e,n){return function(r){var a=e.getPointsAtEvent||e.getBarsAtEvent||e.getSegmentsAtEvent;if(a){var o=a.call(e,r);t[n](o,r),t.$apply()}}}function c(r,a){for(var o=t.copy(a.colours||n.getOptions(r).colours||e.defaults.global.colours);o.length<a.data.length;)o.push(a.getColour());return o.map(u)}function u(t){return"object"==typeof t&&null!==t?t:"string"==typeof t&&"#"===t[0]?s(d(t.substr(1))):l()}function l(){var t=[f(0,255),f(0,255),f(0,255)];return s(t)}function s(t){return{fillColor:h(t,.2),strokeColor:h(t,1),pointColor:h(t,1),pointStrokeColor:"#fff",pointHighlightFill:"#fff",pointHighlightStroke:h(t,.8)}}function f(t,e){return Math.floor(Math.random()*(e-t+1))+t}function h(t,e){return"rgba("+t.concat(e).join(",")+")"}function d(t){var e=parseInt(t,16),n=e>>16&255,r=e>>8&255,a=255&e;return[n,r,a]}function g(e,n,r,a){return{labels:e,datasets:n.map(function(e,n){var o=t.copy(a[n]);return o.label=r[n],o.data=e,o})}}function p(t,e,n){return t.map(function(t,r){return{label:t,value:e[r],color:n[r].strokeColor,highlight:n[r].pointHighlightStroke}})}function v(t,e){var n=t.parent(),r=n.find("chart-legend"),a="<chart-legend>"+e.generateLegend()+"</chart-legend>";r.length?r.replaceWith(a):n.append(a)}function y(t,e,n){Array.isArray(n.data[0])?t.datasets.forEach(function(t,n){(t.points||t.bars).forEach(function(t,r){t.value=e[n][r]})}):t.segments.forEach(function(t,n){t.value=e[n]}),t.update(),n.$emit("update",t)}function C(t){return!t||Array.isArray(t)&&!t.length||"object"==typeof t&&!Object.keys(t).length}return function(e){return{restrict:"CA",scope:{data:"=",labels:"=",options:"=",series:"=",colours:"=?",getColour:"=?",chartType:"=",legend:"@",click:"=",hover:"="},link:function(n,a){function i(r,i){if(!C(r)&&!t.equals(r,i)){var u=e||n.chartType;u&&(c&&c.destroy(),c=o(u,n,a))}}var c,u=document.createElement("div");u.className="chart-container",a.replaceWith(u),u.appendChild(a[0]),"object"==typeof window.G_vmlCanvasManager&&null!==window.G_vmlCanvasManager&&"function"==typeof window.G_vmlCanvasManager.initElement&&window.G_vmlCanvasManager.initElement(a[0]),n.$watch("data",function(t,i){if(t&&t.length&&(!Array.isArray(t[0])||t[0].length)){var u=e||n.chartType;if(u){if(c){if(r(t,i))return y(c,t,n);c.destroy()}c=o(u,n,a)}}},!0),n.$watch("series",i,!0),n.$watch("labels",i,!0),n.$watch("options",i,!0),n.$watch("colours",i,!0),n.$watch("chartType",function(e,r){C(e)||t.equals(e,r)||(c&&c.destroy(),c=o(e,n,a))}),n.$on("$destroy",function(){c&&c.destroy()})}}}}e.defaults.global.responsive=!0,e.defaults.global.multiTooltipTemplate="<%if (datasetLabel){%><%=datasetLabel%>: <%}%><%= value %>",e.defaults.global.colours=["#97BBCD","#DCDCDC","#F7464A","#46BFBD","#FDB45C","#949FB1","#4D5360"],t.module("chart.js",[]).provider("ChartJs",n).factory("ChartJsFactory",["ChartJs",r]).directive("chartBase",["ChartJsFactory",function(t){return new t}]).directive("chartLine",["ChartJsFactory",function(t){return new t("Line")}]).directive("chartBar",["ChartJsFactory",function(t){return new t("Bar")}]).directive("chartRadar",["ChartJsFactory",function(t){return new t("Radar")}]).directive("chartDoughnut",["ChartJsFactory",function(t){return new t("Doughnut")}]).directive("chartPie",["ChartJsFactory",function(t){return new t("Pie")}]).directive("chartPolarArea",["ChartJsFactory",function(t){return new t("PolarArea")}])});
//# sourceMappingURL=angular-chart.min.js.map                                                                  angular-chart.min.js.map                                                                            000644  000000  000000  00000041365 12524077307 013670  0                                                                                                    ustar 00                                                                000000  000000                                                                                                                                                                         {"version":3,"sources":["angular-chart.min.js"],"names":["factory","define","amd","exports","module","require","angular","Chart","ChartJsProvider","options","ChartJs","getOptions","type","typeOptions","extend","this","setOptions","customOptions","$get","ChartJsFactory","canUpdateChart","newVal","oldVal","length","Array","isArray","reduce","sum","carry","val","createChart","scope","elem","data","getColour","getRandomColour","colours","getColours","cvs","ctx","getContext","getDataSets","labels","series","getData","chart","$emit","forEach","action","getEventHandler","legend","setLegend","evt","atEvent","getPointsAtEvent","getBarsAtEvent","getSegmentsAtEvent","activePoints","call","$apply","copy","defaults","global","push","map","convertColour","colour","hexToRgb","substr","getRandomInt","fillColor","rgba","strokeColor","pointColor","pointStrokeColor","pointHighlightFill","pointHighlightStroke","min","max","Math","floor","random","alpha","concat","join","hex","bigint","parseInt","r","g","b","datasets","item","i","dataSet","label","value","color","highlight","$parent","parent","$oldLegend","find","generateLegend","replaceWith","append","updateChart","values","dataset","points","bars","dataItem","j","segments","segment","update","isEmpty","Object","keys","restrict","chartType","click","hover","link","resetChart","equals","destroy","container","document","createElement","className","appendChild","window","G_vmlCanvasManager","initElement","$watch","$on","responsive","multiTooltipTemplate","provider","directive"],"mappings":"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","file":"angular-chart.min.js","sourcesContent":["(function (factory) {\n  'use strict';\n  if (typeof define === 'function' && define.amd) {\n    // AMD. Register as an anonymous module.\n    define(['angular', 'chart.js'], factory);\n  } else if (typeof exports === 'object') {\n    // Node/CommonJS\n    module.exports = factory(require('angular'), require('chart.js'));\n  } else {\n    // Browser globals\n    factory(angular, Chart);\n  }\n}(function (angular, Chart) {\n  'use strict';\n\n  Chart.defaults.global.responsive = true;\n  Chart.defaults.global.multiTooltipTemplate = '<%if (datasetLabel){%><%=datasetLabel%>: <%}%><%= value %>';\n\n  Chart.defaults.global.colours = [\n    '#97BBCD', // blue\n    '#DCDCDC', // light grey\n    '#F7464A', // red\n    '#46BFBD', // green\n    '#FDB45C', // yellow\n    '#949FB1', // grey\n    '#4D5360'  // dark grey\n  ];\n\n  angular.module('chart.js', [])\n    .provider('ChartJs', ChartJsProvider)\n    .factory('ChartJsFactory', ['ChartJs', ChartJsFactory])\n    .directive('chartBase', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory(); }])\n    .directive('chartLine', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Line'); }])\n    .directive('chartBar', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Bar'); }])\n    .directive('chartRadar', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Radar'); }])\n    .directive('chartDoughnut', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Doughnut'); }])\n    .directive('chartPie', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('Pie'); }])\n    .directive('chartPolarArea', ['ChartJsFactory', function (ChartJsFactory) { return new ChartJsFactory('PolarArea'); }]);\n\n  /**\n   * Wrapper for chart.js\n   * Allows configuring chart js using the provider\n   *\n   * angular.module('myModule', ['chart.js']).config(function(ChartJsProvider) {\n   *   ChartJsProvider.setOptions({ responsive: true });\n   *   ChartJsProvider.setOptions('Line', { responsive: false });\n   * })))\n   */\n  function ChartJsProvider () {\n    var options = {};\n    var ChartJs = {\n      Chart: Chart,\n      getOptions: function (type) {\n        var typeOptions = type && options[type] || {};\n        return angular.extend({}, options, typeOptions);\n      }\n    };\n\n    /**\n     * Allow to set global options during configuration\n     */\n    this.setOptions = function (type, customOptions) {\n      // If no type was specified set option for the global object\n      if (! customOptions) {\n        customOptions = type;\n        options = angular.extend(options, customOptions);\n        return;\n      }\n      // Set options for the specific chart\n      options[type] = angular.extend(options[type] || {}, customOptions);\n    };\n\n    this.$get = function () {\n      return ChartJs;\n    };\n  }\n\n  function ChartJsFactory (ChartJs) {\n    return function chart (type) {\n      return {\n        restrict: 'CA',\n        scope: {\n          data: '=',\n          labels: '=',\n          options: '=',\n          series: '=',\n          colours: '=?',\n          getColour: '=?',\n          chartType: '=',\n          legend: '@',\n          click: '=',\n          hover: '='\n        },\n        link: function (scope, elem/*, attrs */) {\n          var chart, container = document.createElement('div');\n          container.className = 'chart-container';\n          elem.replaceWith(container);\n          container.appendChild(elem[0]);\n\n          if (typeof window.G_vmlCanvasManager === 'object' && window.G_vmlCanvasManager !== null) {\n            if (typeof window.G_vmlCanvasManager.initElement === 'function') {\n              window.G_vmlCanvasManager.initElement(elem[0]);\n            }\n          }\n\n          // Order of setting \"watch\" matter\n\n          scope.$watch('data', function (newVal, oldVal) {\n            if (! newVal || ! newVal.length || (Array.isArray(newVal[0]) && ! newVal[0].length)) return;\n            var chartType = type || scope.chartType;\n            if (! chartType) return;\n\n            if (chart) {\n              if (canUpdateChart(newVal, oldVal)) return updateChart(chart, newVal, scope);\n              chart.destroy();\n            }\n\n            chart = createChart(chartType, scope, elem);\n          }, true);\n\n          scope.$watch('series', resetChart, true);\n          scope.$watch('labels', resetChart, true);\n          scope.$watch('options', resetChart, true);\n          scope.$watch('colours', resetChart, true);\n\n          scope.$watch('chartType', function (newVal, oldVal) {\n            if (isEmpty(newVal)) return;\n            if (angular.equals(newVal, oldVal)) return;\n            if (chart) chart.destroy();\n            chart = createChart(newVal, scope, elem);\n          });\n\n          scope.$on('$destroy', function () {\n            if (chart) chart.destroy();\n          });\n\n          function resetChart (newVal, oldVal) {\n            if (isEmpty(newVal)) return;\n            if (angular.equals(newVal, oldVal)) return;\n            var chartType = type || scope.chartType;\n            if (! chartType) return;\n\n            // chart.update() doesn't work for series and labels\n            // so we have to re-create the chart entirely\n            if (chart) chart.destroy();\n\n            chart = createChart(chartType, scope, elem);\n          }\n        }\n      };\n    };\n\n    function canUpdateChart (newVal, oldVal) {\n      if (newVal && oldVal && newVal.length && oldVal.length) {\n        return Array.isArray(newVal[0]) ?\n        newVal.length === oldVal.length && newVal[0].length === oldVal[0].length :\n          oldVal.reduce(sum, 0) > 0 ? newVal.length === oldVal.length : false;\n      }\n      return false;\n    }\n\n    function sum (carry, val) {\n      return carry + val;\n    }\n\n    function createChart (type, scope, elem) {\n      if (! scope.data || ! scope.data.length) return;\n      scope.getColour = typeof scope.getColour === 'function' ? scope.getColour : getRandomColour;\n      scope.colours = getColours(type, scope);\n      var cvs = elem[0], ctx = cvs.getContext('2d');\n      var data = Array.isArray(scope.data[0]) ?\n        getDataSets(scope.labels, scope.data, scope.series || [], scope.colours) :\n        getData(scope.labels, scope.data, scope.colours);\n      var options = angular.extend({}, ChartJs.getOptions(type), scope.options);\n      var chart = new ChartJs.Chart(ctx)[type](data, options);\n      scope.$emit('create', chart);\n\n      ['hover', 'click'].forEach(function (action) {\n        if (scope[action]) cvs[action === 'click' ? 'onclick' : 'onmousemove'] = getEventHandler(scope, chart, action);\n      });\n      if (scope.legend && scope.legend !== 'false') setLegend(elem, chart);\n      return chart;\n    }\n\n    function getEventHandler (scope, chart, action) {\n      return function (evt) {\n        var atEvent = chart.getPointsAtEvent || chart.getBarsAtEvent || chart.getSegmentsAtEvent;\n        if (atEvent) {\n          var activePoints = atEvent.call(chart, evt);\n          scope[action](activePoints, evt);\n          scope.$apply();\n        }\n      };\n    }\n\n    function getColours (type, scope) {\n      var colours = angular.copy(scope.colours ||\n        ChartJs.getOptions(type).colours ||\n        Chart.defaults.global.colours\n      );\n      while (colours.length < scope.data.length) {\n        colours.push(scope.getColour());\n      }\n      return colours.map(convertColour);\n    }\n\n    function convertColour (colour) {\n      if (typeof colour === 'object' && colour !== null) return colour;\n      if (typeof colour === 'string' && colour[0] === '#') return getColour(hexToRgb(colour.substr(1)));\n      return getRandomColour();\n    }\n\n    function getRandomColour () {\n      var colour = [getRandomInt(0, 255), getRandomInt(0, 255), getRandomInt(0, 255)];\n      return getColour(colour);\n    }\n\n    function getColour (colour) {\n      return {\n        fillColor: rgba(colour, 0.2),\n        strokeColor: rgba(colour, 1),\n        pointColor: rgba(colour, 1),\n        pointStrokeColor: '#fff',\n        pointHighlightFill: '#fff',\n        pointHighlightStroke: rgba(colour, 0.8)\n      };\n    }\n\n    function getRandomInt (min, max) {\n      return Math.floor(Math.random() * (max - min + 1)) + min;\n    }\n\n    function rgba (colour, alpha) {\n      return 'rgba(' + colour.concat(alpha).join(',') + ')';\n    }\n\n    // Credit: http://stackoverflow.com/a/11508164/1190235\n    function hexToRgb (hex) {\n      var bigint = parseInt(hex, 16),\n        r = (bigint >> 16) & 255,\n        g = (bigint >> 8) & 255,\n        b = bigint & 255;\n\n      return [r, g, b];\n    }\n\n    function getDataSets (labels, data, series, colours) {\n      return {\n        labels: labels,\n        datasets: data.map(function (item, i) {\n          var dataSet = angular.copy(colours[i]);\n          dataSet.label = series[i];\n          dataSet.data = item;\n          return dataSet;\n        })\n      };\n    }\n\n    function getData (labels, data, colours) {\n      return labels.map(function (label, i) {\n        return {\n          label: label,\n          value: data[i],\n          color: colours[i].strokeColor,\n          highlight: colours[i].pointHighlightStroke\n        };\n      });\n    }\n\n    function setLegend (elem, chart) {\n      var $parent = elem.parent(),\n          $oldLegend = $parent.find('chart-legend'),\n          legend = '<chart-legend>' + chart.generateLegend() + '</chart-legend>';\n      if ($oldLegend.length) $oldLegend.replaceWith(legend);\n      else $parent.append(legend);\n    }\n\n    function updateChart (chart, values, scope) {\n      if (Array.isArray(scope.data[0])) {\n        chart.datasets.forEach(function (dataset, i) {\n          (dataset.points || dataset.bars).forEach(function (dataItem, j) {\n            dataItem.value = values[i][j];\n          });\n        });\n      } else {\n        chart.segments.forEach(function (segment, i) {\n          segment.value = values[i];\n        });\n      }\n      chart.update();\n      scope.$emit('update', chart);\n    }\n\n    function isEmpty (value) {\n      return ! value ||\n        (Array.isArray(value) && ! value.length) ||\n        (typeof value === 'object' && ! Object.keys(value).length);\n    }\n\n  }\n}));\n"],"sourceRoot":"/source/"}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           