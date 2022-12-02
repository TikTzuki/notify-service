import React from "react";
import {Template} from "../../types/Template";
import {useHistory} from "react-router-dom";

interface IProps {
    templates: Array<Template>;
    onEdit: (template: Template) => void;
    onDelete: (template: Template) => void;
}

const TemplateTable = (props: IProps) => {
    const history = useHistory()
    return (
        <div className="template-table">
            <h1>View templates</h1>
            <button onClick={() => {
                history.push("/template/detail")
            }}> ADD TEMPLATE
            </button>
            <table>
                <thead>
                <tr>
                    <th>id</th>
                    <th>title</th>
                    <th>description</th>
                    <th>type</th>
                    <th>actions</th>
                </tr>
                </thead>
                <tbody>
                {props.templates.length > 0 ? (
                    props.templates.map(i => (
                        <tr key={i.id}>
                            <td>{i.title}</td>
                            <td>{i.description}</td>
                            <td>{i.type}</td>
                            <td>
                                <button onClick={() => {
                                    // history.push("/template/detail")
                                }}>edit
                                </button>
                                <button onClick={() => props.onDelete(i)}>delete</button>
                            </td>
                        </tr>
                    ))
                ) : (
                    <tr>
                        <td colSpan={3}>no templates</td>
                    </tr>
                )}
                </tbody>
            </table>
        </div>
    )
}

export default TemplateTable