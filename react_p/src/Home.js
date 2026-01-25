import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './css/Home.css';

function Home() {

    const allPosts = Array.from({ length: 50 }, (_, index) => ({
        id: index + 1,
        title: `${index + 1}번째 게시글`,
        content: `${index + 1}번째 게시글의 내용입니다.`,
    }));

    const postsPerPage = 10;
    const [currentPage, setCurrentPage] = useState(1);

    // 현재 페이지에 해당하는 게시글 리스트 계산
    const indexOfLastPost = currentPage * postsPerPage;
    const indexOfFirstPost = indexOfLastPost - postsPerPage;
    const currentPosts = allPosts.slice(indexOfFirstPost, indexOfLastPost);

    // 전체 페이지 번호 배열 계산
    const totalPages = Math.ceil(allPosts.length / postsPerPage);
    const pageNumbers = Array.from({ length: totalPages }, (_, index) => index + 1);

    // 페이지 변경 핸들러
    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    return (
        <div className="home-container">
            <h1 className="home-title">게시글 목록</h1>
            <div className="posts-list">
                {currentPosts.map(post => (
                    <div key={post.id} className="post-card">
                        <h2 className="post-title">
                            <Link to={`/post/${post.id}`}>{post.title}</Link>
                        </h2>
                        <p className="post-content">{post.content}</p>
                    </div>
                ))}
            </div>

            {/* 페이지 번호 네비게이션 */}
            <div className="pagination">
                {pageNumbers.map((number) => (
                    <button
                        key={number}
                        className={`page-btn ${number === currentPage ? 'active' : ''}`}
                        onClick={() => handlePageChange(number)}
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
