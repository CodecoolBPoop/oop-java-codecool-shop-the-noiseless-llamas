window.addEventListener('load', function(){
    let category = document.getElementById('category');
    let supplier = document.getElementById('supplier');
    category.addEventListener('change', function(){
        window.location = '/?category_id=' + this.value + '&supplier_id=' + supplier.value ;
    }, false);
    supplier.addEventListener('change', function(){
        window.location = '/?category_id=' + category.value + '&supplier_id=' + this.value ;
    }, false);
}, false);