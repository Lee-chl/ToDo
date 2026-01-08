import React from 'react'
import {
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    IconButton
} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined"

class Todo extends React.Component {
    constructor(props) {
        super(props)
        this.state = {item: props.item, readOnly: true}; //타이틀 수정할 수있는 상태로 변경하기 위해 read Only 상태 추가
        this.delete = props.delete;
        this.update = props.update;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item)
    }

    //readonly 모드 false로 변경 (수정 가능)
    offReadOnlyMode = () => {
        this.setState({readOnly: false});
    }

    //read only 모드 true로 변경 (수정 불가)
    enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            this.setState({readOnly: true});
            this.update(this.state.item)
        }
    };

    // 수정
    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.message = e.target.value;
        this.setState({item: thisItem});
    }

    //check box 체크 변경
    checkboxEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({item: thisItem});
        this.update(this.state.item); //check box 변경 시 저장
    }

    render() {
        const item = this.state.item;
        return (
            <ListItem
                secondaryAction={
                    <IconButton
                        aria-label="Delete Todo"
                        onClick={this.deleteEventHandler}>
                        <DeleteOutlined/>
                    </IconButton>
                }
                disablePadding
            >
                <Checkbox color="secondary" checked={item.done} onChange={this.checkboxEventHandler}/>
                <ListItemText>
                    <InputBase  //mui에서 사용하는 inputBase 에는 이미 readOnly라는 props가 있다
                        inputProps={{
                            "aria-label": "naked",
                            readOnly: this.state.readOnly,
                        }}
                        onClick={this.offReadOnlyMode}
                        onKeyPress={this.enterKeyEventHandler}
                        onChange={this.editEventHandler}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.message}
                        multiline={true}
                        fullWidth={true}
                    />
                </ListItemText>
            </ListItem>
        );
    }
}

export default Todo;