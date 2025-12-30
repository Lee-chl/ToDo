import React from 'react';
import './App.css';
import Todo from "./Todo";
import AddTodo from "./AddTodo";
import {call, signout} from './service/ApiService'
import {Paper, List, Container, Grid, Button, AppBar, Toolbar, Typography} from "@mui/material";
import {Add} from "@mui/icons-material";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            // 로딩 중이라는 상태 표현할 변수 생성자의 상태 변수 추가
            loading: true,
        };
    }

    componentWillMount() {
        call("/todo", "GET", null).then((response) =>
            this.setState({items: response.data, loading: false}) // get 요청 성공 시 loading false
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

        // navigationBar 추가
        let navigationBar = (
            <AppBar position="static">
                <Toolbar>
                    <Grid justify="space-between" container>
                        <Grid item>
                            <Typography variant="h6">오늘의 할일</Typography>
                        </Grid>
                        <Grid>
                            <Button color="inherit" onClick={signout}>
                                로그아웃
                            </Button>
                        </Grid>
                    </Grid>
                </Toolbar>
            </AppBar>
        );
        // 로딩 중이 아닐 때 랜더링할 부분
        let todoListPage = (
            <div>
                {navigationBar}
                <Container maxWidth="md">
                    <AddTodo add={this.add}/>
                    <div className="TodoList">{todoItems}</div>
                </Container>
            </div>
        );

        // 로딩 중일 때 랜더링 할 부분
        let loadingPage = <h1> 로딩중.. </h1>;
        let content = loadingPage;
        if (!this.state.loading) {
            // 로딩 중이 아니면 todoListPage 선택
            content = todoListPage;
        }

        // 선택한 content 렌더링
        return (
            <div className="App">{content}</div>
        );
    }
}

export default App;
