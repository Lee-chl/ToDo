import React from "react";
import {TextField, Paper, Button, Grid} from "@mui/material";

class AddTodo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {item: {message: ""}}; //사용자 입력 저장할 오브젝트
        this.add = props.add; // props 함수를 this.add에 연결
    }

    //입력한 문자 저장
    onInputChange = (e) => {
        const thisItem = this.state.item;
        thisItem.message = e.target.value;
        this.setState({item: thisItem});
    }

    //버튼 클릭
    onButtonClick = () => {
        this.add(this.state.item); //상위 컴포넌트(App.js)의 add 함수 사용
        this.setState({item: {message: ""}});
    }

    // Enter 입력 시 아이템 추가
    enterKeyEventHandler = (e) => {
        if (e.key === 'Enter') {
            this.onButtonClick();
        }
    }

    render() {
        return (
            <Paper style={{margin: 16, padding: 16}}>
                <Grid container>
                    <Grid xs={11} md={11} item style={{paddingRight: 16}}>
                        <TextField
                            placeholder="Add Todo here" fullWidth
                            onChange={this.onInputChange}
                            value={this.state.item.message}
                            onKeyPress={this.enterKeyEventHandler}
                        />
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button fullWidth
                                color="secondary"
                                variant="outlined"
                                onClick={this.onButtonClick}
                        >
                            +
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        );
    }
}

export default AddTodo;