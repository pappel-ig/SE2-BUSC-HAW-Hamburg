import React from "react";
import {User} from "../Frame";
import {Navigate, useNavigate} from "react-router-dom";
import axios from "axios";

type FormData = {
    username: string,
    password: string
}


type RegisterProps = {
    user: User,
}

type RegisterState = {
    registered: boolean
    formData: FormData
}

export class Register extends React.Component<RegisterProps, RegisterState> {

    constructor(props: RegisterProps) {
        super(props)
        this.state = {
            registered: false,
            formData: {username: "", password: ""}
        };
        this.onChange = this.onChange.bind(this);
        this.register = this.register.bind(this);
    }

    onChange(event: React.FormEvent<HTMLInputElement>) {
        this.setState({
            ...this.state,
            formData: {
                ...this.state.formData,
                [event.currentTarget.name]: event.currentTarget.value
            }
        });
    };

    register(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        axios.post('/user/register', this.state.formData)
            .then(value => this.setState({registered: true}));
    }

    render() {
        if (this.state.registered) return (<Navigate to={"/login"}/>)
        if (this.props.user.loggedIn) return (<Navigate to={"/profile"}/>)
        else return (
            <div>
                <h1>Registriere dich für Mensa Match</h1>
                <form onSubmit={this.register}>
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