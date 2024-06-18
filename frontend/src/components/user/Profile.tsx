import {User} from "../Frame";
import React from "react";
import {Navigate} from "react-router-dom";
import axios from "axios";
import {Multiselect} from 'multiselect-react-dropdown';
const budget = 0.666; //Hier Budget

type ProfileProps = {
    user: User,
    loginStateChanged: Function
}

type Criteria = {
    value: string
    label: string
}

type ProfileState = {
    criteria: Criteria[]
    include: Criteria[]
    exclude: Criteria[]
}

export class Profile extends React.Component<ProfileProps, ProfileState> {
    constructor(props: ProfileProps) {
        super(props)
        this.state = {
            criteria: [],
            include: [],
            exclude: []
        };
        this.logout = this.logout.bind(this);
    }

    logout() {
        axios.post('/api/user/logout')
            .then(value => this.props.loginStateChanged());
    }

    componentDidMount() {
        axios.get("/api/criteria")
            .then(value => {
                this.setState({
                    ...this.state,
                    include: Object.keys(value.data.include).map((key) => ({value: key, label: value.data.include[key]})),
                    exclude: Object.keys(value.data.exclude).map((key) => ({value: key, label: value.data.exclude[key]}))
                });
            })
        axios.get('/api/criteria/all')
            .then(value => Object.keys(value.data).map((key) => ({value: key, label: value.data[key]})))
            .then(value => this.setState({
                ...this.state,
                criteria: value
            }))
    }

    private onSelectInclude(_: Criteria[], selectedItem: Criteria) {
        axios.post('/api/criteria/include/' + selectedItem.value)
    }

    private onRemoveInclude(_: Criteria[], removedItem: Criteria) {
        axios.delete('/api/criteria/include/' + removedItem.value)
    }

    private onSelectExclude(_: Criteria[], selectedItem: Criteria) {
        axios.post('/api/criteria/exclude/' + selectedItem.value)
    }

    private onRemoveExclude(_: Criteria[], removedItem: Criteria) {
        axios.delete('/api/criteria/exclude/' + removedItem.value)
    }

    render() {
        if (!this.props.user.loggedIn) return (<Navigate to={"/login"}/>)
        else return (
            <div className="boxinbox2">
                <h1>Hello {this.props.user.username}</h1>
                <button onClick={this.logout}>Logout</button>
                <br/>
                <h3>Budget</h3>
                <div className="budget-box">
                    <p>{budget}</p>
                </div>
                <br/>
                <h3>Aktive Positiv Filter</h3>
                <Multiselect
                    id={"positive"}
                    options={this.state.criteria}
                    selectedValues={this.state.include}
                    onSelect={this.onSelectInclude}
                    onRemove={this.onRemoveInclude}
                    displayValue="label"
                />

                <h3>Aktive Negativ Filter</h3>
                <Multiselect
                    id={"negative"}
                    options={this.state.criteria}
                    selectedValues={this.state.exclude}
                    onSelect={this.onSelectExclude}
                    onRemove={this.onRemoveExclude}
                    displayValue="label"
                />
            </div>
        )
    }
}
