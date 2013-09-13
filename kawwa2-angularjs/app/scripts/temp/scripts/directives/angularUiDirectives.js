/**
 * @ngdoc directive
 * @name kawwa2.directive:accordion
 * @description
 * From Angular-UI : Render an accordion
 * require the directive collapse
 *
 * @restrict A
 *
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 angular.module("moduleApp")
     .controller('AccordionCtrl', function ($scope) {
         $scope.oneAtATime = true;

         $scope.groups = [
             {
                 title: "Dynamic Group Header - 1",
                 content: "Dynamic Group Body - 1"
             },
             {
                 title: "Dynamic Group Header - 2",
                 content: "Dynamic Group Body - 2"
             }
         ];

         $scope.items = ['Item 1', 'Item 2', 'Item 3'];

         $scope.addItem = function() {
             var newItemNo = $scope.items.length + 1;
             $scope.items.push('Item ' + newItemNo);
         };
         
     });

 </script>
 <h4>Accordion (from angular-ui)</h4>
 <article data-ng-controller="AccordionCtrl" class="k-article">
     <label class="checkbox">
         <input type="checkbox" ng-model="oneAtATime">
         Open only one at a time
     </label>

     <div data-accordion data-close-others="oneAtATime">
         <div data-accordion-group data-heading="Static Header">
             This content is straight in the template.
         </div>
         <div data-accordion-group data-heading="Dynamic Body Content">
             <p>The body of the accordion group grows to fit the contents</p>
             <button class="btn btn-small" ng-click="addItem()">Add Item</button>
             <div ng-repeat="item in items">{{item}}</div>
         </div>
         <div data-accordion-group data-heading="{{group.title}}" data-ng-repeat="group in groups">
             {{group.content}}
         </div>
         <div data-accordion-group>
             <div data-accordion-heading>
                 I can have markup, too! <i class="icon-check"></i>
             </div>
             This is just some content to illustrate fancy headings.
         </div>
     </div>
 </article>

 </doc:source>

 </doc:example>
 */

/**
 * @ngdoc directive
 * @name kawwa2.directive:collapse
 * @description
 * From Angular-UI : Collapse
 *
 * @restrict A
 *
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 angular.module('moduleApp')
     .controller('CollapseCtrl', function ($scope) {
         $scope.isCollapsed = false;

     });

 </script>
 <h4>COLLAPSIBLE PANEL From angular-ui</h4>
 <article data-ng-controller="CollapseCtrl" class="k-article">
     <input type="submit" ng-click="isCollapsed = !isCollapsed" value="Toggle collapse" />
     <hr>
     <div data-collapse="isCollapsed">
         <p>Hello Collapse panel</p>
     </div>

 </article>






 </doc:source>

 </doc:example>
 */

/**
 * @ngdoc directive
 * @name kawwa2.directive:btnCheckBox
 * @description
 * From Angular-UI Check Box and Radio Button
 *
 * @restrict A
 *
 *
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>

 angular.module('moduleApp').
     controller('KButtonCtrl', function ($scope) {
         $scope.model = 1;

         $scope.checkModel = {
             left: false,
             center: true,
             right: false
         };

         $scope.radioValues = {
             left:'Left',
             center:'Center',
             right:'Right'
         };
         $scope.radioModel = 'Center';
         

     });

 </script>
 <h4>Button (from angular-ui)</h4>
 <h5>Simple Check Box</h5>
 <div data-ng-controller="KButtonCtrl">
         <pre>{{model}}</pre>
 <form action="">
     <input type="submit" class="optional" btn-checkbox ng-model="model" value="Optional Action" />
 </form>
 <h5>CheckBox Button</h5>

         <pre>{{checkModel |json}}</pre>
 <form action="">
     <input type="submit" class="optional" btn-checkbox ng-model="checkModel.left" value="Left" />
     <input type="submit" class="optional" btn-checkbox ng-model="checkModel.center" value="Center" />
     <input type="submit" class="optional" btn-checkbox ng-model="checkModel.right" value="Right" />
 </form>

 <h5>Radio Button</h5>

 <pre>{{radioModel |json}}</pre>
 <form action="">
     <input type="submit" class="optional" btn-radio="radioValues.left" ng-model="radioModel" value="Left" />
     <input type="submit" class="optional" btn-radio="radioValues.center" ng-model="radioModel" value="Center" />
     <input type="submit" class="optional" btn-radio="radioValues.right" ng-model="radioModel" value="Right" />
 </form>
 </div>


 </doc:source>

 </doc:example>
 */

/**
 * @ngdoc directive
 * @name kawwa2.directive:carousel
 * @description
 * From Angular-UI Check Box and Radio Button
 * @restrict EA
 * @example
 <doc:example module="moduleApp">
 <doc:source>
 <script>
 angular.module('moduleApp')
 .controller('CarouselCtrl', function ($scope) {
         $scope.myInterval = 5000;
         var slides = $scope.slides = [];
         $scope.addSlide = function() {
             var newWidth = 200 + ((slides.length + (25 * slides.length)) % 150);
             slides.push({
                 image: 'http://placekitten.com/' + newWidth + '/200',
                 text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
                     ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
             });
         };
         for (var i=0; i<4; i++) {
             $scope.addSlide();
         }
     });
 </script>
 <h4>Carousel From Angular-ui  (not integreted)</h4>


     <div data-ng-controller="CarouselCtrl" class="k-intro">
     <div data-carousel interval="myInterval">
         <div  data-slide ng-repeat="slide in slides" active="slide.active">
             <img ng-src="{{slide.image}}" style="margin:auto;">
             <div class="carousel-caption">
                 <h4>Slide {{$index}}</h4>
                 <p>{{slide.text}}</p>
             </div>
         </div>
     </div>
     <section>
         <div>
             <ul class="k-links-list">
                 <li ng-repeat="slide in slides">
                     <input type="submit" class="optional" ng-class="{'btn-info': !slide.active, 'btn-success': slide.active}" ng-disabled="slide.active" ng-click="slide.active = true" value="select"/>
                     {{$index}}: {{slide.text}}
                 </li>
             </ul>
             <input type="submit" class="optional" ng-click="addSlide()" value="Add Slide">
         </div>
         <div>
             Interval, in milliseconds: <input type="number" ng-model="myInterval">
             <br />Enter a negative number to stop the interval.
         </div>
     </section>
 </div>
 </doc:source>
 </doc:example>
 */



