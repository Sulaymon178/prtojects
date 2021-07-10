function generateFormData(data) {
    var a = $('.form-element');
    Array.from(a).map(item => {
        item.value = data.object[item.name]
    })
}
