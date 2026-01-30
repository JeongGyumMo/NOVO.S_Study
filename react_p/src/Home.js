import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './css/Home.css';
import api from "./api/axios";

function Home() {
    const [posts, setPosts] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [loginUser, setLoginUser] = useState(null);
    const postsPerPage = 10;

    const navigate = useNavigate();

    // 게시글 목록
    const getPostList = () => {
        api.get("/post", {
            params: {
                page: currentPage - 1,
                size: postsPerPage
            }
        })
        .then(response => {
            setPosts(response.data.content);
            setTotalPages(response.data.totalPages);
        })
        .catch(error => {
            console.error('게시글 가져오기 실패:', error);
        });
    };

    // 로그인 상태 확인
    const checkLogin = () => {
        api.get("/users/me")
        .then(res => {
            setLoginUser(res.data);
        })
        .catch(() => {
            setLoginUser(null);
        });
    };

    useEffect(() => {
        getPostList();
        checkLogin();
    }, [currentPage]);

    const handleLogout = async () => {
        try {
            await api.post("/users/logout");
            alert("로그아웃 되었습니다");
            setLoginUser(null);
            navigate("/");
        } catch (error) {
            console.error("로그아웃 실패", error);
        }
    };

    return (
        <div className="home-container">
            <div style={{ display: "flex", justifyContent: "flex-end", gap: "10px", marginBottom: "50px" }}>
                {loginUser ? (
                    <>
                        <span>👋 {loginUser}님</span>
                        <button onClick={handleLogout}>로그아웃</button>
                    </>
                ) : (
                    <>
                        <button onClick={() => navigate("/login")}>로그인</button>
                        <button onClick={() => navigate("/signup")}>회원가입</button>
                    </>
                )}
            </div>

            <h1 className="home-title">게시글 목록</h1>

            <div className="posts-list">
                {posts.map(post => (
                    <div key={post.id} className="post-card">
                        <h2 className="post-title">
                            <Link to={`/post/${post.id}`}>{post.title}</Link>
                        </h2>
                        <p className="post-content">{post.content}</p>
                    </div>
                ))}
            </div>

            <div className="pagination">
                {Array.from({ length: totalPages }, (_, i) => i + 1).map(number => (
                    <button
                        key={number}
                        className={`page-btn ${number === currentPage ? 'active' : ''}`}
                        onClick={() => setCurrentPage(number)}
                    >
                        {number}
                    </button>
                ))}
            </div>

            <Link to="/create" className="create-link">게시글 작성하기</Link>
        </div>
    );
}

export default Home;
