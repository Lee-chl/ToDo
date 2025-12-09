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
        this.state = {item: props.item};
        this.delete = props.delete;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item)
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
                <Checkbox checked={item.done}/>
                <ListItemText>
                    <InputBase
                        inputProps={{"aria-label": "naked"}}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                    />
                </ListItemText>
            </ListItem>
        );
    }
}

export default Todo;