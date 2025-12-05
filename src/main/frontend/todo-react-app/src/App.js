import React from 'react';
import './App.css';
import Todo from "./Todo";
import {Paper, List} from "@mui/material";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [
                {id: 0, message: "Hello", done: true},
                {id: 1, message: "Hello2", done: false}
            ],
        };
    }

    render() {
        let todoItems = this.state.items.length > 0 && (
            <Paper style={{margin: 16}}>
                <List>
                    {this.state.items.map((item) => (
                        <Todo item={item} key={item.id}/>
                    ))}
                </List>
            </Paper>
        );
        return <div className="App">{todoItems}</div>;
    }
}

export default App;
