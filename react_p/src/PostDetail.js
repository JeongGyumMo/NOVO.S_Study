import React, { useEffect, useState, useCallback } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from "axios";
import './css/PostDetail.css';

function PostDetail() {
    const { id } = useParams();

    const [post, setPost] = useState({ title: "", content: "" });
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");
    const [loginUser, setLoginUser] = useState(null);

    const API = process.env.REACT_APP_API_URL;

    const getLoginUser = useCallback(() => {
        axios.get(`${API}/users/me`, { withCredentials: true })
            .then(res => setLoginUser(res.data))
            .catch(() => setLoginUser(null));
    }, [API]);

    const getPost = useCallback(() => {
        axios.get(`${API}/post/${id}`)
            .then(res => setPost(res.data))
            .catch(err => console.error(err));
    }, [API, id]);

    const getComments = useCallback(() => {
        axios.get(`${API}/comments/${id}`)
            .then(res => setComments(res.data))
            .catch(err => console.error(err));
    }, [API, id]);

    useEffect(() => {
        getPost();
        getComments();
        getLoginUser();
    }, [getPost, getComments, getLoginUser]);

    const handleCommentSubmit = () => {
        if (!newComment.trim()) return;

        axios.post(
            `${API}/comments?postId=${id}`,
            { content: newComment },
            { withCredentials: true }
        ).then(() => {
            setNewComment("");
            getComments();
        }).catch(err => console.error(err));
    };

    const handleCommentDelete = (commentId) => {
        axios.delete(
            `${API}/comments/${commentId}`,
            { withCredentials: true }
        ).then(() => {
            getComments();
        }).catch(err => console.error(err));
    };

    return (
        <div className="post-detail-container">
            <h1 className="post-detail-title">{post.title}</h1>
            <p className="post-detail-content">{post.content}</p>

            <Link to="/" className="back-link">목록으로 돌아가기</Link>

            <div className="comment-section">
                <h2>댓글</h2>

                <div className="comment-input-box">
                    <textarea
                        value={newComment}
                        onChange={(e) => setNewComment(e.target.value)}
                        placeholder="댓글을 입력하세요"
                    />
                    <button onClick={handleCommentSubmit}>작성</button>
                </div>

                <ul className="comment-list">
                    {comments.map(comment => (
                        <li key={comment.id} className="comment-item">
                            <div className="comment-header">
                                <div className="comment-meta">
                                    <span className="comment-writer">{comment.writer}</span>
                                    <span className="comment-dot">•</span>
                                    <span className="comment-date">
                                        {comment.createdAt?.replace('T',' ').slice(0,16)}
                                    </span>
                                </div>

                                {loginUser && comment.writer === loginUser.username && (
                                    <button
                                        className="comment-delete-btn"
                                        onClick={() => handleCommentDelete(comment.id)}
                                    >
                                        삭제
                                    </button>
                                )}
                            </div>

                            <p className="comment-text">{comment.content}</p>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default PostDetail;
