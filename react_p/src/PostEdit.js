import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import './css/PostEdit.css';

function PostEdit() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    useEffect(() => {
        setTitle(`${id}번째 게시글 제목`);
        setContent(`${id}번째 게시글의 상세 내용입니다.`);
    }, [id]);

    const handleSubmit = (e) => {
        e.preventDefault();
        alert('게시글이 수정되었습니다.');
        navigate(`/post/${id}`);
    };

    return (
        <div className="post-edit-container">
            <h1 className="post-edit-title">게시글 수정</h1>
            <form onSubmit={handleSubmit} className="post-edit-form">
                <div className="form-group">
                    <label htmlFor="title">제목</label>
                    <input
                        id="title"
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="제목을 입력하세요"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="content">내용</label>
                    <textarea
                        id="content"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        placeholder="내용을 입력하세요"
                    />
                </div>
                <div className="button-group">
                    <button type="submit" className="submit-button">수정 완료</button>
                    <Link to={`/post/${id}`} className="cancel-button">취소</Link>
                </div>
            </form>
        </div>
    );
}

export default PostEdit;
