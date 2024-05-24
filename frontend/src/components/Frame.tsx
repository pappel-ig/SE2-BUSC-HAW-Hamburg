import React from "react";
import {Criteria} from "./meal/Criteria";
import axios from "axios";
import {Header} from "./Header";
import {Menu} from "./Menu";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Tagesplan from "./meal/Tagesplan";
import {Profile} from "./user/Profile";
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
    constructor(props: {}) {
        super(props)
        this.state = {
            user: {
                "username": '',
                loggedIn: false
            }
        }
        this.determineLoginState = this.determineLoginState.bind(this);
        this.loginStateChanged = this.loginStateChanged.bind(this);
    }

    componentDidMount() {
        this.determineLoginState();
    }

    determineLoginState() {
        axios.get('/api/user')
            .then(res => this.setState({ user: res.data }))
    }

    loginStateChanged() {
        this.determineLoginState();
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
                            <Route path="/profile" element={<Profile loginStateChanged={this.loginStateChanged} user={this.state.user}/>}/>
                            <Route path="/recommend" element={<Empfehlung user={this.state.user}/>}/>
                            <Route path="/login" element={<Login loginStateChanged={this.loginStateChanged} user={this.state.user}/>}/>
                            <Route path="/register" element={<Register user={this.state.user}/>}/>
                        </Routes>
                    </div>
                </BrowserRouter>
            </div>)
    }
}