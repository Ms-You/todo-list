import { Paper, List, Container } from "@material-ui/core";
import Todo from './Todo';
import AddTodo from './AddTodo.js';
import './App.css';
import React from 'react';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = { 
      items: [
        { id: "0", title: "Hello world 1", done: true },
        { id: "1", title: "Hello world 2", done: false},
      ],
    };
  }

  // add 함수 추가
  add = (item) => {
    const thisItems = this.state.items;
    item.id = "ID-" + thisItems.length;
    item.done = false;  // done 초기화
    thisItems.push(item);
    this.setState({ items: thisItems });
    console.log("items : ", this.state.items);
  }

  render() {
    var todoItems = this.state.items.length > 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {this.state.items.map((item, idx) => (
            <Todo item={item} key={item.id} />
          ))}
        </List>
      </Paper>
    );

    // add 함수 연결
    return (
      <div className='App'>
        <Container maxWidth="md">
          <AddTodo add={this.add} />
          <div className="TodoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;
