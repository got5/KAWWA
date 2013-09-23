angular.module('kawwa2').run(['$templateCache', function ($templateCache) {
	$templateCache.put('template/accordion/accordion-group.html', '<div> <h3 class="control" ng-click="isOpen=!isOpen" accordion-transclude="heading">{{heading}}</h3> <div collapse="!isOpen"> <div class="content" ng-transclude></div> </div> </div>');
	$templateCache.put('template/accordion/accordion.html', '<div class="k-accordion" ng-transclude></div>');
	$templateCache.put('template/alert/alert.html', '<div class=\'alert\' ng-class=\'type && "alert-" + type\'> <button ng-show=\'closeable\' type=\'button\' class=\'close\' ng-click=\'close()\'>&times;</button> <div ng-transclude></div> </div> ');
	$templateCache.put('template/carousel/carousel.html', '<div ng-mouseenter="pause()" ng-mouseleave="play()" class="carousel"> <ol class="carousel-indicators" ng-show="slides().length> 1"> <li ng-repeat="slide in slides()" ng-class="{active: isActive(slide)}" ng-click="select(slide)"></li> </ol> <div class="carousel-inner" ng-transclude></div> <a ng-click="prev()" class="carousel-control left" ng-show="slides().length> 1">&lsaquo;</a> <a ng-click="next()" class="carousel-control right" ng-show="slides().length> 1">&rsaquo;</a> </div> ');
	$templateCache.put('template/carousel/slide.html', '<div ng-class="{ \'active\': leaving || (active && !entering), \'prev\': (next || active) && direction==\'prev\', \'next\': (next || active) && direction==\'next\', \'right\': direction==\'prev\', \'left\': direction==\'next\' }" class="item" ng-transclude></div> ');
	$templateCache.put('template/datepicker/datepicker.html', '<table> <thead> <tr class="text-center"> <th><button type="button" class="btn pull-left" ng-click="move(-1)"><i class="icon-chevron-left"></i></button></th> <th colspan="{{rows[0].length - 2 + showWeekNumbers}}"><button type="button" class="btn btn-block" ng-click="toggleMode()"><strong>{{title}}</strong></button></th> <th><button type="button" class="btn pull-right" ng-click="move(1)"><i class="icon-chevron-right"></i></button></th> </tr> <tr class="text-center" ng-show="labels.length> 0"> <th ng-show="showWeekNumbers">#</th> <th ng-repeat="label in labels">{{label}}</th> </tr> </thead> <tbody> <tr ng-repeat="row in rows"> <td ng-show="showWeekNumbers" class="text-center"><em>{{ getWeekNumber(row) }}</em></td> <td ng-repeat="dt in row" class="text-center"> <button type="button" style="width:100%;" class="btn" ng-class="{\'btn-info\': dt.selected}" ng-click="select(dt.date)" ng-disabled="dt.disabled"><span ng-class="{muted: dt.secondary}">{{dt.label}}</span></button> </td> </tr> </tbody> </table> ');
	$templateCache.put('template/datepicker/popup.html', '<ul class="dropdown-menu" ng-style="{display: (isOpen && \'block\') || \'none\', top: position.top+\'px\', left: position.left+\'px\'}" class="dropdown-menu"> <li ng-transclude></li> <li class="divider"></li> <li style="padding: 9px;"> <span class="btn-group"> <button class="btn btn-small btn-inverse" ng-click="today()">Today</button> <button class="btn btn-small btn-info" ng-click="showWeeks=! showWeeks" ng-class="{active: showWeeks}">Weeks</button> <button class="btn btn-small btn-danger" ng-click="clear()">Clear</button> </span> <button class="btn btn-small btn-success pull-right" ng-click="isOpen=false">Close</button> </li> </ul> ');
	$templateCache.put('template/dialog/message.html', '<div class="modal-header"> <h3>{{ title }}</h3> </div> <div class="modal-body"> <p>{{ message }}</p> </div> <div class="modal-footer"> <button ng-repeat="btn in buttons" ng-click="close(btn.result)" class="btn" ng-class="btn.cssClass">{{ btn.label }}</button> </div> ');
	$templateCache.put('template/modal/backdrop.html', '<div class="modal-backdrop fade" ng-class="{in: animate}" ng-style="{\'z-index\': 1040 + index*10}" ng-click="close($event)"></div>');
	$templateCache.put('template/modal/window.html', '<div class="modal fade {{ windowClass }}" ng-class="{in: animate}" ng-style="{\'z-index\': 1050 + index*10}" ng-transclude></div>');
	$templateCache.put('template/pagination/pager.html', '<div class="pager"> <ul> <li ng-repeat="page in pages" ng-class="{disabled: page.disabled, previous: page.previous, next: page.next}"><a ng-click="selectPage(page.number)">{{page.text}}</a></li> </ul> </div> ');
	$templateCache.put('template/pagination/pagination.html', '<div class="pagination"><ul> <li ng-repeat="page in pages" ng-class="{active: page.active, disabled: page.disabled}"><a ng-click="selectPage(page.number)">{{page.text}}</a></li> </ul> </div> ');
	$templateCache.put('template/pane.html', '<div class="tab-pane" ng-class="{\'ui-tabs-hide\': !selected}" ng-transclude></div>');
	$templateCache.put('template/popover/popover.html', '<div class="popover {{placement}}" ng-class="{ in: isOpen(), fade: animation() }"> <div class="arrow"></div> <div class="popover-inner"> <h3 class="popover-title" ng-bind="title" ng-show="title"></h3> <div class="popover-content" ng-bind="content"></div> </div> </div> ');
	$templateCache.put('template/productCatalog.html', '<article class="k-product hproduct"> <div class="photo-data"> <p> <a href="#"> <img class="photo" ng-src="{{options.urlImage}}/{{product.id}}.jpg"/> </a> </p> </div> <p class="extra new" ng-if="product.isNew">{{options.newText}}</p> <h3 class="fn name"> <a ng-href="{{options.urlProduct}}/{{product.id}}" title="See Product sheet">{{product.name}}</a> </h3> <p data-product-rating="options.rating(product)"> <span ng-show="product.comments"> <a ng-href="{{options.urlComment}}/{{product.id}}">{{product.length}} {{options.commentText}}</a> </span> <span ng-hide="product.comments"> {{options.noCommentText}} </span> </p> <div class="price-data"> <p class="all-prices"> <strong class="price new">{{product.price | currency:options.currency}}</strong> </p> </div> <div class="buyZone"> <p class="buy"> <a href="#" ng-click="options.add(product)">{{options.basketText}}</a> </p> </div> </article>');
	$templateCache.put('template/productGallery.html', '<div class="k-product-gallery photo-data"> <p> <a class="jqzoom" rel="{{title}}" href="{{gallery[0].hd}}" title="{{gallery[0].title}}"> <img class="photo" ng-src="{{gallery[0].small}}" alt="{{gallery[0].title}}"/> </a> </p> <ul class="thumblist"> <li ng-repeat="image in gallery" ng-class="{zoomThumbActive : $index==0}"> <a href="#" rel="{gallery: \'{{title}}\', smallimage:\'{{image.small}}\',largeimage:\'{{image.hd}}\'}"> <img ng-src="{{image.thumb}}" alt="{{image.title}}"/></a></li> </ul> </div> ');
	$templateCache.put('template/progressbar/bar.html', '<div class="bar" ng-class=\'type && "bar-" + type\'></div>');
	$templateCache.put('template/progressbar/progress.html', '<div class="progress"><progressbar ng-repeat="bar in bars" width="bar.to" old="bar.from" animate="bar.animate" type="bar.type"></progressbar></div>');
	$templateCache.put('template/rating/rating.html', '<span ng-mouseleave="reset()"> <i ng-repeat="r in range" ng-mouseenter="enter($index + 1)" ng-click="rate($index + 1)" ng-class="$index <val && (r.stateOn || \'icon-star\') || (r.stateOff || \'icon-star-empty\')"></i> </span>');
	$templateCache.put('template/tabs.html', '<div class="k-tabbed-data"> <ul class="tabs"> <li ng-repeat="pane in panes" ng-class="{\'ui-state-active\':pane.selected}"> <a href="#" ng-click="select(pane)">{{pane.title}}</a> </li> </ul> <section class="content" ng-transclude></section> </div>');
	$templateCache.put('template/tabs/tab.html', '<li ng-class="{ui-state-active: active}"> <a ng-click="select()" tab-heading-transclude>{{heading}}</a> </li>');
	$templateCache.put('template/tabs/tabset-titles.html', '<ul class="tabs"> </ul> ');
	$templateCache.put('template/tabs/tabset.html', '<div class="tabbable"> <div tabset-titles="tabsAbove"></div> <div class="tab-content"> <div class="tab-pane" ng-repeat="tab in tabs" ng-class="{active: tab.active}" tab-content-transclude="tab"> </div> </div> <div tabset-titles="!tabsAbove"></div> </div> ');
	$templateCache.put('template/timepicker/timepicker.html', '<table class="form-inline"> <tr class="text-center"> <td><a ng-click="incrementHours()" class="btn btn-link"><i class="icon-chevron-up"></i></a></td> <td>&nbsp;</td> <td><a ng-click="incrementMinutes()" class="btn btn-link"><i class="icon-chevron-up"></i></a></td> <td ng-show="showMeridian"></td> </tr> <tr> <td class="control-group" ng-class="{\'error\': invalidHours}"><input type="text" ng-model="hours" ng-change="updateHours()" class="span1 text-center" ng-mousewheel="incrementHours()" ng-readonly="readonlyInput" maxlength="2"/></td> <td>:</td> <td class="control-group" ng-class="{\'error\': invalidMinutes}"><input type="text" ng-model="minutes" ng-change="updateMinutes()" class="span1 text-center" ng-readonly="readonlyInput" maxlength="2"></td> <td ng-show="showMeridian"><button type="button" ng-click="toggleMeridian()" class="btn text-center">{{meridian}}</button></td> </tr> <tr class="text-center"> <td><a ng-click="decrementHours()" class="btn btn-link"><i class="icon-chevron-down"></i></a></td> <td>&nbsp;</td> <td><a ng-click="decrementMinutes()" class="btn btn-link"><i class="icon-chevron-down"></i></a></td> <td ng-show="showMeridian"></td> </tr> </table>');
	$templateCache.put('template/tooltip/tooltip-html-unsafe-popup.html', '<div class="tooltip {{placement}}" ng-class="{ in: isOpen(), fade: animation() }"> <div class="tooltip-arrow"></div> <div class="tooltip-inner" ng-bind-html-unsafe="content"></div> </div> ');
	$templateCache.put('template/tooltip/tooltip-popup.html', '<div class="tooltip {{placement}}" ng-class="{ in: isOpen(), fade: animation() }"> <div class="tooltip-arrow"></div> <div class="tooltip-inner" ng-bind="content"></div> </div> ');
	$templateCache.put('template/typeahead/typeahead-match.html', '<a tabindex="-1" bind-html-unsafe="match.label | typeaheadHighlight:query"></a>');
	$templateCache.put('template/typeahead/typeahead-popup.html', '<ul class="typeahead dropdown-menu" ng-style="{display: isOpen()&&\'block\' || \'none\', top: position.top+\'px\', left: position.left+\'px\'}"> <li ng-repeat="match in matches" ng-class="{active: isActive($index) }" ng-mouseenter="selectActive($index)" ng-click="selectMatch($index)"> <typeahead-match index="$index" match="match" query="query" template-url="templateUrl"></typeahead-match> </li> </ul>');
}]);