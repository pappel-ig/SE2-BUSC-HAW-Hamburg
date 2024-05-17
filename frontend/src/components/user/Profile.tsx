import {User} from "../Frame";
import React from "react";
import {Navigate} from "react-router-dom";
import axios from "axios";
import {Multiselect} from 'multiselect-react-dropdown';
const budget = 0.666; //Hier Budget
const filters: string[] = [];

/*Testfilter*/
filters.push("vegan");
filters.push("vegetarisch");
filters.push("enthält keine laktosehaltigen Lebensmittel");

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
    selected: Criteria[]
}

export class Profile extends React.Component<ProfileProps, ProfileState> {
    constructor(props: ProfileProps) {
        super(props)
        this.state = {
            criteria: [],
            selected: []
        };
        this.logout = this.logout.bind(this);
    }

    logout() {
        axios.post('user/logout')
            .then(value => this.props.loginStateChanged());
    }

    componentDidMount() {
        axios.get("criteria")
            .then(value => Object.keys(value.data).map((key) => ({value: key, label: value.data[key]})))
            .then(value => this.setState({
                ...this.state,
                selected: value
            }))
        axios.get('criteria/all')
            .then(value => Object.keys(value.data).map((key) => ({value: key, label: value.data[key]})))
            .then(value => this.setState({
                ...this.state,
                criteria: value
            }))
    }

    private onSelect(_: Criteria[], selectedItem: Criteria) {
        axios.post('criteria/' + selectedItem.value)
    }

    private onRemove(_: Criteria[], removedItem: Criteria) {
        axios.delete('criteria/' + removedItem.value)
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
                <h3>Aktive Filter</h3>
                <Multiselect
                    options={this.state.criteria}
                    selectedValues={this.state.selected}
                    onSelect={this.onSelect}
                    onRemove={this.onRemove}
                    displayValue="label"
                />

                <div className="boxinbox3">
                    {/*<p>{this.state.criteria.join(" ,")}</p>*/}
                </div>
            </div>
        )
    }
}
