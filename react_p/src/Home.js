import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './css/Home.css';
import api from "./api/axios";

function Home() {
    const [posts, setPosts] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [loginUser, setLoginUser] = useState(null);
    const [keyword, setKeyword] = useState("");
    const [searchInput, setSearchInput] = useState("");

    const postsPerPage = 10;
    const navigate = useNavigate();

    useEffect(() => {

        const getPostList = () => {
            api.get("/post", {
                params: {
                    page: currentPage - 1,
                    size: postsPerPage,
                    keyword: keyword
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

        const checkLogin = () => {
            api.get("/users/me")
            .then(res => setLoginUser(res.data))
            .catch(() => setLoginUser(null));
        };

        getPostList();
        checkLogin();

    }, [currentPage, keyword]);

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

    const handleCreateClick = () => {
        if (!loginUser) {
            alert("로그인이 필요한 서비스입니다. 🔒");
            navigate("/login");
            return;
        }
        navigate("/create");
    };

    const handleDelete = async (postId) => {
        if (!window.confirm("정말 삭제하시겠습니까?")) return;

        try {
            await api.delete(`/post/${postId}`);
            alert("삭제 완료");
            setPosts(prev => prev.filter(p => p.id !== postId));
        } catch (err) {
            alert("삭제 실패");
        }
    };

    const handleSearch = () => {
        setCurrentPage(1);
        setKeyword(searchInput);
    };

    const handleKeyDown = (e) => {
        if (e.key === "Enter") handleSearch();
    };

    return (
        <div className="home-container">
            <div className="search-bar">
                <input
                    type="text"
                    placeholder="제목 또는 내용 검색"
                    value={searchInput}
                    onChange={(e) => setSearchInput(e.target.value)}
                    onKeyDown={handleKeyDown}
                />
                <button onClick={handleSearch}>검색</button>
            </div>

            <div className="top-bar">
                {loginUser ? (
                    <>
                        <span>👋 {loginUser.username}님</span>
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
                {posts.length === 0 ? (
                    <div className="no-result">
                        🔍 검색 결과가 없습니다.
                    </div>
                ) : (
                    posts.map(post => (
                        <div key={post.id} className="post-card">

                            <p className="post-writer">작성자 ID: {post.writer}</p>

                            <h2 className="post-title">
                                <Link to={`/post/${post.id}`}>{post.title}</Link>
                            </h2>

                            <p className="post-content">{post.content}</p>

                            {loginUser && loginUser.username === post.writer && (
                                <div className="post-actions">
                                    <button
                                        className="edit-btn"
                                        onClick={() => navigate(`/post/edit/${post.id}`)}
                                    >
                                        수정
                                    </button>
                                    <button
                                        className="delete-btn"
                                        onClick={() => handleDelete(post.id)}
                                    >
                                        삭제
                                    </button>
                                </div>
                            )}
                        </div>
                    ))
                )}
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

            <button
                onClick={handleCreateClick}
                className={loginUser ? "create-btn active" : "create-btn"}
            >
                게시글 작성하기
            </button>
        </div>
    );
}

export default Home;