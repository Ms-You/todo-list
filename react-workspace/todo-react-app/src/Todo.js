import React from 'react';
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton } from "@material-ui/core";
import { DeleteOutlined } from '@material-ui/icons';

class Todo extends React.Component {
    constructor(props){
        super(props);   // props 오브젝트 초기화
        this.state = { item: props.item };
        this.delete = props.delete;
    }

    // 삭제처리 핸들러 추가
    deleteEventHandler = () => {
        this.delete(this.state.item);
    }

    render() {
    const item = this.state.item;
    return (
        <ListItem>
            <Checkbox checked={item.done} disableRipple />
            <ListItemText>
                <InputBase
                    inputProps={{ "aria-label": "naked" }}
                    type="text"
                    id={item.id}
                    name={item.id}
                    value={item.title}
                    multiline={true}
                    fullWidth={true}
                />
            </ListItemText>

            <ListItemSecondaryAction>
                <IconButton
                    aria-label="Delete Todo"
                    onClick={this.deleteEventHandler}>
                    <DeleteOutlined />
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem>
        );
    }
}

export default Todo;
