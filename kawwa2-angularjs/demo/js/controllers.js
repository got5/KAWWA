  var myAppModule = angular.module('myApp', ['kawwa'])

    function TestCtrl($scope) {


       // $scope.yourName = "Emmanuel DEMEY";

        $scope.raty = 2;
        $scope.json={value: "test"};
        $scope.selected="";

        var baseImagePath = '/demo/'
        $scope.gallery = [
            {
                title: "first image",
                thumb: baseImagePath + 'img/productGallery/1s.png',
                small: baseImagePath + 'img/productGallery/1m.png',
                hd: baseImagePath + 'img/productGallery/1hd.png'
            }, {
                title: 'second image',
                thumb: baseImagePath + 'img/productGallery/2s.png',
                small: baseImagePath + 'img/productGallery/2m.png',
                hd: baseImagePath + 'img/productGallery/2hd.png'
            }, {
                title: 'third image',
                thumb: baseImagePath + 'img/productGallery/3s.png',
                small: baseImagePath + 'img/productGallery/3m.png',
                hd: baseImagePath + 'img/productGallery/3hd.png'
            }

        ];
        $scope.galleryOptions = {
            zoomType:'innerzoom',
            lens: false,
            preloadImages: true,
            alwaysOn: false
        };




    }