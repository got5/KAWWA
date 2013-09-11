angular.module('moduleApp').controller('ProductCatalogCtrl', function ($scope) {

    $scope.basket = [];

    $scope.product = {
        "id": 1,
        "name": "AngularJS",
        "author": "Brad Green, Shyam Seshadri",
        "price": 15.34,
        "description": "Description...",
        "category": "book",
        "isNew": true,
        "comments": [
            {
                "rate": 2,
                "user": "Toto",
                "comment": "Test comment"
            },
            {
                "rate": 5,
                "user": "Jojo",
                "comment": "Test comment"
            },
            {
                "rate": 3,
                "user": "Fifi",
                "comment": "Test comment"
            }
        ]
    };

    //make the rating of the product from the average of the comments
    var productRating = function(pItem){
        if (pItem.comments) {
            var sumRatings = 0;
            for ( var index = 0; index < pItem.comments.length; index++) {
                var comment = pItem.comments[index];
                sumRatings += comment.rate;
            }
            return Math.floor(sumRatings / pItem.comments.length);
        }
        return 0;
    }

    //minimalist basket
    var addToBasket = function (product) {

        var i = $scope.basket.indexOf(product);
        if(i ==-1){
            product.quantity = 1;
            $scope.basket.push(product);

        }else{
            $scope.basket[i].quantity++;
        }
    };

    $scope.options={
        "urlImage":'img/catalog',
        "urlProduct":'#/product/detail',
        "urlComment":'#/product/comment',
        "rating":productRating,
        "add":addToBasket,
        "basketText":"Ajouter au panier",
        "currency":'€',
        "newText":'nouv'
    };

    $scope.view = 'block';

});