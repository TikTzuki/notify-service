import React, {Component} from "react";
import TemplateTable from "./TemplateTable";
import {Template} from "../../types/Template";
import {useHistory} from "react-router-dom";

class Home extends Component<{}, { templates: Template[] }> {
    constructor(props) {
        super(props);
        this.state = {
            templates: []
        };
    }

    componentDidMount() {
        fetch('/api/templates')
            .then(response => {
                console.log(response)
                return response.json()
            })
            .then(data => {
                console.log(data)
                this.setState({templates: data.data.content})
            });
    }

    async remove(template: Template) {

    }

    edit(template: Template) {
        useHistory().push("/template/detail")
    }

    render() {
        return (
            <div>

                <TemplateTable
                    templates={this.state.templates}
                    onEdit={this.edit}
                    onDelete={this.remove}
                />
            </div>
        );
    }
}

export default Home;