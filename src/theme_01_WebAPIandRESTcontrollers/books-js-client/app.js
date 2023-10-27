let reloadBooksButton = document.getElementById('reloadBooks');
reloadBooksButton.addEventListener("click", reloadBooks)

let booksContainer = document.getElementById("books-container");

function reloadBooks() {
    booksContainer.innerHTML = '';
    
    fetch('http://localhost:8080/api/books')
        .then(rsp => rsp.json())
        .then(json => json.forEach(book => {
            let bookRow = document.createElement('tr');
            let titleCol = document.createElement('td');
            let authorCol = document.createElement('td');
            let isbnCol = document.createElement('td');
            let actionBtn = document.createElement('td');
            let deleteBtn = document.createElement('button');

            titleCol.textContent = book.title;
            authorCol.textContent = book.author.name;
            isbnCol.textContent = book.isbn;
            deleteBtn.textContent = 'Delete';
            deleteBtn.dataset.id = book.id;

            actionBtn.appendChild(deleteBtn);

            bookRow.appendChild(titleCol);
            bookRow.appendChild(authorCol);
            bookRow.appendChild(isbnCol);
            bookRow.appendChild(actionBtn);

            booksContainer.appendChild(bookRow)

            deleteBtn.addEventListener('click', deleteBtnClicked);
        }))
}

function deleteBtnClicked(e) {

    let bookId = e.target.dataset.id;

    let requestOptions = {
        method: 'DELETE'
    }

    fetch(`http://localhost:8080/api/books/${bookId}`, requestOptions)
        .then(_ => reloadBooks())
        .catch(error => console.log('error', error));
}