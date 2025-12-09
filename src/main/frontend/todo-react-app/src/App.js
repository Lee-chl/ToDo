import React from 'react';
import './App.css';
import Todo from "./Todo";
import AddTodo from "./AddTodo";
import {Paper, List, Container} from "@mui/material";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [
                {id: 0, title: "Hello", done: true},
                {id: 1, title: "Hello2", done: false}
            ],
        };
    }

    // list add
    add = (item) => {
        const thisItems = this.state.items;
        item.id = "ID-" + thisItems.length; //key 위한 id 추가
        item.done = false;
        thisItems.push(item);
        this.setState({items: thisItems}); // 업데이트는 반드시 this.setState로 해야함
    }

    // delete
    delete = (item) => {
        const thisItems = this.state.items;
        const newItems = thisItems.filter(e => e.id !== item.id);
        this.setState({items: newItems}, () => {
            //디버깅 콜백
            console.log("Update Items : ", this.state.items)
        });
    }

    render() {
        let todoItems = this.state.items.length > 0 && (
            <Paper style={{margin: 16}}>
                <List>
                    {this.state.items.map((item) => (
                        <Todo item={item} key={item.id} delete={this.delete}/>
                    ))}
                </List>
            </Paper>
        );
        return (
            <div className="App">
                <Container maxWidth="md">
                    <AddTodo add={this.add}/>
                    <div className="TodoList">{todoItems}</div>
                </Container>
            </div>
        );
    }
}

export default App;
