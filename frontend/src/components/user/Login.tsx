import React from "react";
import axios from "axios";
import {User} from "../Frame";
import {Navigate, redirect, useNavigate} from "react-router-dom";

type FormData = {
    username: string,
    password: string
}

type LoginState = {
    formData: FormData
}

type LoginProps = {
    user: User,
    loginStateChanged: Function
}

export class Login extends React.Component<LoginProps, LoginState> {
    constructor(props: LoginProps) {
        super(props)
        this.state = {
            formData: {username: "", password: ""}
        };
        this.onChange = this.onChange.bind(this);
        this.login = this.login.bind(this);
    }

    onChange(event: React.FormEvent<HTMLInputElement>) {
        this.setState({
            formData: {
                ...this.state.formData,
                [event.currentTarget.name]: event.currentTarget.value
            }
        });
    };

    login(event: React.FormEvent<HTMLFormElement>) {
        const res = axios.post('/user/login', this.state.formData)
            .then(_ => this.props.loginStateChanged())
        event.preventDefault();
    }

    render() {
        if (this.props.user.loggedIn) return (<Navigate to={"/profile"}/>)
        return (
            <div>
                <h1>Login für Mensa Match</h1>
                <form onSubmit={this.login}>
                    <label htmlFor="username">Benutzername:</label><br/>
                    <input type="text" id="username" name="username" value={this.state.formData.username}
                           onChange={this.onChange}/><br/>
                    <label htmlFor="password">Password:</label><br/>
                    <input type="password" id="password" name="password" value={this.state.formData.password}
                           onChange={this.onChange}/>
                    <br/>
                    <input type="submit" value="Submit"/>
                </form>
            </div>
        )
    }
}