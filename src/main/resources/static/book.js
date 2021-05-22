'use strict'
const baseURL = window.location;

// for usage check our previous project gossip-talks
function f(path, body=null, json=true) {
  var url = new URL(baseURL);
  url.pathname = path;
  return (body == null ? fetch(url) : fetch(url, { method: 'post', body: body, headers: new Headers({'content-type': 'application/json'}) }))
    .then((response) => {
      console.log(response);
      if (response.status >= 400) {
        var err = `Error #${response.status}`;
        throw err;
      }
      return json ? response.json() : response;
    });
}

function editBook(bookId){
   var form = $('#editor');
   var elements = ["id", "title", "author", "genre", "price"];
   f(`/api/v1/books/${bookId}`)
    .then((book) => {
       elements.forEach(field => {
         // fill form fields with data from json object
         form.find('#' + field).val(book[field])
       })
    })
}

function deleteBook(bookId){
  fetch(`/api/v1/books/${bookId}`, {method: 'delete'})
    .then((response) => getBooks());
}

function getBooks() {
  f('/api/v1/books')
    .then((data) => {
      var table = $('#bookTable tbody').empty();
      data.forEach(book => {
        table.append(
         `<tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.genre}</td>
            <td>${book.price}</td>
            <td>
              <a onClick="editBook(${book.id})"><i class="fa fa-edit"></i> </a>
              <a onClick="deleteBook(${book.id})"><i class="fa fa-trash"></i> </a>
            </td>
          </tr>`);
      });
    });
  return false;
}
function saveBook(){
  var form = $('#editor');
  var book = {}
  var elements = ["id", "title", "author", "genre", "price"];
  // copy json fields from form elements
  elements.forEach(field => {
    book[field] = form.find('#' + field).val()
  })
  // if id is set - call update, otherwise - call create
  f(book.id ? `/api/v1/books/${book.id}` : '/api/v1/books', JSON.stringify(book))
    .then((data) => {
      form[0].reset(); // clear form
      getBooks() // reload
    })
  return false;
}
// load books on the page
getBooks();