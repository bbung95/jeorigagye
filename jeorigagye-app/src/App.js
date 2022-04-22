import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter, Routes, Route, Outlet } from "react-router-dom";
import Navigation from "./Routes/Navigation";
import Login from "./Routes/Login";
import MyPage from "./Routes/MyPage";
import Home from "./Routes/Home";

function App() {
  return (
    <div>
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route path="/login" element={<Login />}/>
                    <Route path="/home" element={<Home />}/>
                    <Route path="/mypage" element={<MyPage />}/>
                </Route>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

function Layout(){
    return (
        <div>
            <Navigation/>
            <Outlet/>
        </div>
    );
}

export default App;
