import React from 'react';
import './css/CreatePost.css';

function CreatePost() {
    return (
        <div className="create-post-container">
            <h1 className="create-post-title">게시글 작성</h1>
            <form className="create-post-form">
                <div className="form-group">
                    <label htmlFor="title">제목</label>
                    <input id="title" type="text" placeholder="제목을 입력하세요" />
                </div>
                <div className="form-group">
                    <label htmlFor="content">내용</label>
                    <textarea id="content" placeholder="내용을 입력하세요" />
                </div>
                <button type="submit" className="submit-button">등록</button>
            </form>
        </div>
    );
}

export default CreatePost;
