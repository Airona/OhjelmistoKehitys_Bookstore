class App extends React.Component {
  constructor(props) {
      super(props);
      this.deleteBook = this.deleteBook.bind(this);
      this.createBook = this.createBook.bind(this);
      this.state = {
          books: [],
      };
   }
 
  componentDidMount() {
    this.loadBooksFromServer();
  }
  
  // Load books from database
  loadBooksFromServer() {
      fetch('http://localhost:8080/api/books', {credentials: 'same-origin'}) 
      .then((response) => response.json()) 
      .then((responseData) => { 
          this.setState({ 
              books: responseData._embedded.books, 
          }); 
      });     
  } 
  
  // Delete book
  deleteBook(book) {
      fetch (book._links.self.href,
      {method: 'DELETE', credentials: 'same-origin'})
      .then( 
          res => this.loadBooksFromServer()
      )             
  }  
  
  // Create new book
  createBook(book) {
      fetch('http://localhost:8080/api/books', {
          method: 'POST', credentials: 'same-origin',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(book)
      })
      .then( 
          res => this.loadBooksFromServer()
      )
  }
  
  render() {
    return (
       <div className="text-center">
          <BookTable deleteBook={this.deleteBook} books={this.state.books}/> 
          <BookForm createBook={this.createBook}/>
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
      <table className="table">
        <thead>
          <tr>
            <th>Title</th><th>Author</th><th>Year</th><th>ISBN</th><th></th>
          </tr>
        </thead>
        <tbody>{books}</tbody>
      </table>
      </div>);
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
            <td>
                <button className="btn" onClick={this.deleteBook}>Delete</button>
            </td>
          </tr>
        );
    } 
}

class BookForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {title: '', author: '', year: '', isbn: ''};
        this.handleSubmit = this.handleSubmit.bind(this);   
        this.handleChange = this.handleChange.bind(this);     
    }

    handleChange(event) {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }    
    
    handleSubmit(event) {
        event.preventDefault();
        var newBook = {title: this.state.title, author: this.state.author, year: this.state.year, isbn: this.state.isbn};
        this.props.createBook(newBook);        
    }
    
    render() {
        return (
				<form className="form-inline">
                        <input type="text" placeholder="Title" className="form-control"  name="title" onChange={this.handleChange}/><br />
                        <input type="text" placeholder="Author" className="form-control" name="author" onChange={this.handleChange}/><br />
                        <input type="text" placeholder="Year" className="form-control" name="year" onChange={this.handleChange}/><br />
                        <input type="text" placeholder="ISBN" className="form-control" name="isbn" onChange={this.handleChange}/><br />
                        <button className="btn" onClick={this.handleSubmit}>Save</button>      
                </form>
        );
    }
}

ReactDOM.render(<App />, document.getElementById('root') );