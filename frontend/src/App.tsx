import Tagesplan from "./components/meal/Tagesplan";
import {BrowserRouter, Routes, Route, Link} from "react-router-dom";
import React from "react";
import {Header} from "./components/Header";

function App() {
    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<Tagesplan/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
