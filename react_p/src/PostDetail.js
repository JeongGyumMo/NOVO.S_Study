import React, {useEffect, useState} from 'react';
import {useParams, Link, useNavigate} from 'react-router-dom';
import axios from "axios";
import './css/PostDetail.css';

function PostDetail() {
    const { id } = useParams();
    const navigate = useNavigate();

    const [post, setPost] = useState({ title: "", content: "" });
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");

    const API = process.env.REACT_APP_API_URL;

    // 게시글 가져오기
    const getPost = () => {
        axios.get(`${API}/post/${id}`)
            .then(res => setPost(res.data))
            .catch(err => console.error(err));
    };

    // 댓글 목록 가져오기
    const getComments = () => {
        axios.get(`${API}/comments/${id}`)
            .then(res => setComments(res.data))
            .catch(err => console.error(err));
    };

    useEffect(() => {
        getPost();
        getComments();
    }, [id]);

    // 댓글 작성
    const handleCommentSubmit = () => {
        if (!newComment.trim()) return;

        axios.post(`${API}/comments?postId=${id}`, {
            content: newComment
        }).then(() => {
            setNewComment("");
            getComments();
        }).catch(err => console.error(err));
    };

    // 댓글 삭제
    const handleCommentDelete = (commentId) => {
        axios.delete(`${API}/comments/${commentId}`)
            .then(() => getComments())
            .catch(err => console.error(err));
    };

    // 게시글 삭제
    const handleDelete = () => {
        if (!window.confirm("정말 이 게시글을 삭제하시겠습니까?")) return;

        axios.delete(`${API}/post/${id}`)
            .then(() => {
                alert('게시글이 삭제되었습니다.');
                navigate('/');
            })
            .catch(() => alert('삭제 실패'));
    };

    return (
        <div className="post-detail-container">
            <h1 className="post-detail-title">{post.title}</h1>
            <p className="post-detail-content">{post.content}</p>

            <div className="button-group">
                <Link to={`/post/edit/${id}`} className="edit-button">수정하기</Link>
                <button onClick={handleDelete} className="delete-button">삭제하기</button>
            </div>

            <Link to="/" className="back-link">목록으로 돌아가기</Link>

            {/* 🔥 댓글 영역 */}
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
                            <div>
                                <p>{comment.content}</p>
                                <span>{comment.createdAt?.replace('T', ' ')}</span>
                            </div>
                            <button onClick={() => handleCommentDelete(comment.id)}>삭제</button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default PostDetail;
