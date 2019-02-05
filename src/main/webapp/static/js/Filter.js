window.addEventListener('load', function(){
    let category = document.getElementById('category');
    let supplier = document.getElementById('supplier');
    category.addEventListener('change', function(){
        window.location = '/?category_id=' + this.value + '&supplier_id=' + supplier.value ;
    }, false);
    supplier.addEventListener('change', function(){
        window.location = '/?category_id=' + category.value + '&supplier_id=' + this.value ;
    }, false);
    let add_buttons = document.getElementsByClassName("cart_button");
    for (let i = 0; i < add_buttons.length; i++) {
        let button = add_buttons[i];
        button.addEventListener('click', function (event) {
            let filterString = '/?category_id=' + category.value + '&supplier_id=' + supplier.value;
            window.location = filterString + '&cart_id=' + event.target.dataset.id;
        })
    }
    window.history.replaceState({}, document.title, "/" + '?category_id=' + category.value + '&supplier_id=' + supplier.value);
}, false);