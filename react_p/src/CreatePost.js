import './css/CreatePost.css';
import {useState} from "react";
import axios from "axios";

let CreatePost = () => {

    let [formData, setFormData] = useState({
        title: '',
        content: ''
    });

    let onChangeFormData = (e) => {
        setFormData({
            ...formData,
            [e.target.name] : e.target.value
        });
    };

    const onClickSubmit = () => {
    console.log("API URL:", process.env.REACT_APP_API_URL);

        axios.post(`${process.env.REACT_APP_API_URL}/post`, formData)
            .then(res => {
                console.log(res.data);
            })
            .catch(err => {
                console.error(err);
            })
    }

    return (
        <div className={"create-post-container"}>
            <h1 className={"create-post-title"}>게시글 작성</h1>
            <form className={"create-post-form"}>
                <div className={"form-group"}>
                    <label htmlFor={"title"}>제목</label>
                    <input id={"title"} type={"text"} name={"title"} onChange={onChangeFormData} placeholder={"제목을 입력하세요"}/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor={"content"}>내용</label>
                    <textarea id={"content"} name={"content"} onChange={onChangeFormData} placeholder={"내용을 입력하세요"}/>
                </div>
                <button type={"submit"} className={"submit-button"} onClick={onClickSubmit}>등록</button>
            </form>
        </div>
    );
}

export default CreatePost;
