import './App.css';
import{BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Home from "./Home";
import CreatePost from "./CreatePost";
import PostDetail from "./PostDetail";
import PostEdit from "./PostEdit";
import Signup from "./Signup";


function App() {
  return (
    <Router>
        <div className="App">
            <Routes>
                <Route path={"/"} element={<Home/>}/>
                <Route path={"/create"} element={<CreatePost/>}/>
                <Route path={"/post/:id"} element={<PostDetail/>}/>
                <Route path={"/post/edit/:id"} element={<PostEdit/>}/>
                <Route path="/signup" element={<Signup />} />
            </Routes>
        </div>
    </Router>
  );
}

export default App;
