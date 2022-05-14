import React from 'react';
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton } from "@material-ui/core";
import { DeleteOutlined } from '@material-ui/icons';

class Todo extends React.Component {
    constructor(props){
        super(props);   // props 오브젝트 초기화
        this.state = { item: props.item, readOnly: true };
        this.delete = props.delete;
        this.update = props.update;
    }

    // 삭제처리 핸들러 추가
    deleteEventHandler = () => {
        this.delete(this.state.item);
    };

    // Todo 변경 가능 여부 함수 추가
    offReadOnlyMode = () => {
        console.log("Event!", this.state.readOnly);
        this.setState({ readOnly: false }, () => {
            console.log("ReadOnly? ", this.state.readOnly);
        });
    };

    // Todo 변경 가능 여부 엔터 처리 핸들러 추가
    enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            this.setState({ readOnly: true });
            this.update(this.state.item);
        }
    };

    // 편집 처리 이벤트핸들러
    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
    };

    // 체크박스 변경 처리 핸들러
    checkboxEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({ item: thisItem });
        this.update(this.state.item);
    };


    render() {
    const item = this.state.item;
    return (
        <ListItem>
            <Checkbox checked={item.done} onChange={this.checkboxEventHandler} />
            <ListItemText>
                <InputBase
                    inputProps={{ 
                        "aria-label": "naked",
                        readOnly: this.state.readOnly,
                     }}
                    type="text"
                    id={item.id}
                    name={item.id}
                    value={item.title}
                    multiline={true}
                    fullWidth={true}
                    onClick={this.offReadOnlyMode}
                    onChange={this.editEventHandler}
                    onKeyPress={this.enterKeyEventHandler}
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
