import Tagesplan from "./components/meal/Tagesplan";
import {BrowserRouter, Routes, Route, Link} from "react-router-dom";
import React from "react";
import {Header} from "./components/Header";
import Profile from "./components/user/Profile";

function App() {
    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<Tagesplan/>}/>
                <Route path="/profile" element={<Profile/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
