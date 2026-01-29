import './css/CreatePost.css';
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

let CreatePost = () => {

    const navigate = useNavigate(); // 🔥 추가

    let [formData, setFormData] = useState({
        title: '',
        content: ''
    });

    let onChangeFormData = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const onClickSubmit = (e) => {
        e.preventDefault();

        console.log("API URL:", process.env.REACT_APP_API_URL);

        axios.post(`${process.env.REACT_APP_API_URL}/post`, formData)
            .then(res => {
                console.log(res.data);
                alert("게시글이 등록되었습니다!");
                navigate("/");
            })
            .catch(err => {
                console.error(err);
                alert("등록 실패");
            });
    };

    return (
        <div className={"create-post-container"}>
            <h1 className={"create-post-title"}>게시글 작성</h1>
            <form className={"create-post-form"} onSubmit={onClickSubmit}>
                <div className={"form-group"}>
                    <label htmlFor={"title"}>제목</label>
                    <input
                        id={"title"}
                        type={"text"}
                        name={"title"}
                        onChange={onChangeFormData}
                        placeholder={"제목을 입력하세요"}
                    />
                </div>
                <div className={"form-group"}>
                    <label htmlFor={"content"}>내용</label>
                    <textarea
                        id={"content"}
                        name={"content"}
                        onChange={onChangeFormData}
                        placeholder={"내용을 입력하세요"}
                    />
                </div>
                <button type={"submit"} className={"submit-button"}>등록</button>
            </form>
        </div>
    );
}

export default CreatePost;
