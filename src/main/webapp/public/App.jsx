	class App extends React.Component {
		constructor(props) {
			super(props);
			this.deleteBook = this.deleteBook.bind(this);
			this.state = {
				books: []
			};
		}

		componentDidMount() {
			this.loadBooksFromServer()
		}
		
		loadBooksFromServer() {
     		 fetch('http://localhost:8080/api/books', {credentials: 'same-origin'})
    		 .then((response) => response.json()) 
     		 .then((responseData) => {
     		     this.setState({ 
              		books: responseData._embedded.books
     		     }); 
  		    });     
		} 

		//delete book
		deleteBook(book) {
			fetch (book._links.self.href, {method: 'DELETE', credentials: 'same-origin'})
			.then(res => this.loadBooksFromServer())
			.catch(err => console.error(err))
		}

		render() {			
			return (
				<div>
					<BookTable books={this.state.books} deleteBook={this.deleteBook}/>
				</div>
			);
		}
	}

	class BookTable extends React.Component {
		constructor(props) {
			super(props);
		}

		render() {
			var books = this.props.books.map(book =>
				<Book key={book._links.self.href} book={book} deleteBook={this.props.deleteBook}/>
			);
			return (
      			<div>
      				<table className="table table-striped">
        				<thead>
          				<tr>
            				<th>Title</th>
            				<th>Author</th>
            				<th>Year</th>
            				<th>ISBN</th>
            				<th></th>
          				</tr>
        				</thead>
        				<tbody>{books}</tbody>
      				</table>
				</div>
			);
		}
	}

	class Book extends React.Component {
		constructor(props) {
			super(props);
			this.deleteBook = this.deleteBook.bind(this);
		}
		
		deleteBook() {
			this.props.deleteBook(this.props.book);
		}
		
		render() {
			return (
          		<tr>
            		<td>{this.props.book.title}</td>
					<td>{this.props.book.author}</td>
					<td>{this.props.book.year}</td>
					<td>{this.props.book.isbn}</td>
					<td><button className="btn btn-danger" onClick={this.deleteBook} >Delete</button></td>
            	</tr>
			);
		} 
	}

	ReactDOM.render(<App />, document.getElementById('root') );	