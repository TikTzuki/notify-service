import React, {Component} from "react";
import {HashRouter, NavLink, Route} from "react-router-dom";
import Home from "./templates/Home";
import Trigger from "./trigger/Trigger";
import TemplateEdit from "./templates/TemplateEdit";

class Main extends Component {
    render() {
        return (
            <HashRouter>
                <div>
                    <h1>Simple SPA</h1>
                    <ul className="header">
                        <li><NavLink to="/">Template</NavLink></li>
                        <li><NavLink to="/trigger">Trigger</NavLink></li>
                        <li><NavLink to="/template/detail">Template Detail</NavLink></li>
                    </ul>
                    <div className="content">
                        <Route exact path="/" component={Home}/>
                        <Route path="/trigger" component={Trigger}/>
                        <Route exact path="/template/detail" component={TemplateEdit}/>
                    </div>
                </div>
            </HashRouter>
        );
    }
}

export default Main;