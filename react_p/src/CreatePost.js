import './css/CreatePost.css';
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

let CreatePost = () => {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        title: '',
        content: ''
    });

    const onChangeFormData = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const onClickSubmit = (e) => {
        e.preventDefault();

        axios.post(
            `${process.env.REACT_APP_API_URL}/post`,
            formData,
            {
                withCredentials: true
            }
        )
        .then(res => {
            console.log(res.data);
            alert("게시글이 등록되었습니다!");
            navigate("/");
        })
        .catch(err => {
            console.error(err);
            alert("로그인이 만료되었거나 등록에 실패했습니다.");
            navigate("/login"); // 로그인 안 된 상태면 로그인 페이지로
        });
    };

    return (
        <div className="create-post-container">
            <h1 className="create-post-title">게시글 작성</h1>
            <form className="create-post-form" onSubmit={onClickSubmit}>
                <div className="form-group">
                    <label htmlFor="title">제목</label>
                    <input
                        id="title"
                        type="text"
                        name="title"
                        onChange={onChangeFormData}
                        placeholder="제목을 입력하세요"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="content">내용</label>
                    <textarea
                        id="content"
                        name="content"
                        onChange={onChangeFormData}
                        placeholder="내용을 입력하세요"
                        required
                    />
                </div>
                <button type="submit" className="submit-button">등록</button>
            </form>
        </div>
    );
}

export default CreatePost;
