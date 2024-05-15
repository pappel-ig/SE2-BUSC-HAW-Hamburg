import React from "react";
import {Criteria} from "./meal/Criteria";
import axios from "axios";
import {Header} from "./Header";
import {Menu} from "./Menu";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Tagesplan from "./meal/Tagesplan";
import Profile from "./user/Profile";
import {Login} from "./user/Login";
import {Register} from "./user/Register";
import {Empfehlung} from "./meal/Empfehlung";
import {StartPage} from "./StartPage";

export type User = {
    loggedIn: boolean,
    username: string
}

type FrameState = {
    user: User
}

export class Frame extends React.Component<{}, FrameState> {

    state = {
        user: { username: '', loggedIn: false }
    }

    componentDidMount() {
        axios.get('/user')
            .then(res => this.setState({ user: res.data }))
    }

    render() {
        return (
            <div>
                <BrowserRouter>
                    <Header/>
                    <Menu user={this.state.user}/>
                    <div className={"box"}>
                        <Routes>
                            <Route path="/" element={<StartPage/>}/>
                            <Route path="/menu" element={<Tagesplan/>}/>
                            <Route path="/profile" element={<Profile/>}/>
                            <Route path="/recommend" element={<Empfehlung/>}/>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/register" element={<Register/>}/>
                        </Routes>
                    </div>
                </BrowserRouter>
            </div>)
    }
}