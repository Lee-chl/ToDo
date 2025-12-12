import React from 'react';
import './App.css';
import Todo from "./Todo";
import AddTodo from "./AddTodo";
import {call} from './service/ApiService'
import {Paper, List, Container} from "@mui/material";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
        };
    }

    componentWillMount() {
        call("/todo", "GET", null).then((response) =>
            this.setState({items: response.data})
        );
    }

    // list add
    add = (item) => {
        call("/todo", "POST", item).then((response) =>
            this.setState({items: response.data})
        );
    };

    // delete
    delete = (item) => {
        call("/todo", "DELETE", item).then((response) =>
            this.setState({items: response.data})
        );
    };

    // update
    update = (item) => {
        call("/todo", "PUT", item).then((response) =>
            this.setState({items: response.data})
        );
    };

    render() {
        let todoItems = this.state.items.length > 0 && (
            <Paper style={{margin: 16}}>
                <List>
                    {this.state.items.map((item) => (
                        <Todo item={item} key={item.id} delete={this.delete} update={this.update}/>
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
