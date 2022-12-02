import React, {useState} from "react";
import {TemplateType} from "../../types/enums";

const initTemplate = {
    id: "",
    title: "",
    description: "",
    type: TemplateType.SMS,
    content: "",
    context: "{}",
}

type EnumType = { [key: string]: string | number };
type EnumAsArrayType = {
    key: string;
    value: string | number;
}[];
const enumToArray = (data: EnumType): EnumAsArrayType =>
    Object.keys(data)
        .filter((key) => Number.isNaN(+key))
        .map((key: string) => ({
            key,
            value: data[key],
        }));

const TemplateEdit = (props) => {
    const [formValue, setFormValue] = useState(initTemplate)
    // const [errors, setErrmrsr = useState<>({})
    const onAddUser = (template) => {
        console.log(template)
        template.context = JSON.parse(template.context)
        fetch("/api/templates", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(template)
        }).then((data) => {
            console.log(data)
        })
    }

    const onFormSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const rules = [
            {key: "name", required: true, label: "Name"},
            {key: "profession", required: true, label: "Profession"},
            {key: "age", required: true, label: "Age"},
            {key: "name", maxLength: 16, label: "name"},
            {key: "name", minLength: 4, label: "name"},
            {key: "age", minValue: 18, label: "Age"},
            {key: "age", maxValue: 60, label: "Age"}
        ];
        onAddUser(formValue)
        // validator(
        //     formValue,
        //     rules,
        //     (errors: any): any => {
        //         if (noErrors(errors)) {
        //             onAddUser(formValue);
        //             setFormValue(initTemplate);
        //             return false;
        //         }
        //         (errors) => {
        //         };
        //     }
        // );
    };
    const onInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setFormValue({...formValue, [name]: value});
    };

    return (
        <div className="template-form">
            <h1>Add template</h1>
            <form className="form-edit" onSubmit={onFormSubmit}>
                <div className="form-row">
                    <label>Title</label>
                    <input
                        type="text"
                        placeholder="please input title"
                        name="title"
                        value={formValue.title}
                        onChange={onInputChange}
                    />
                    {/*{errors["name"] && errors["name"].length > 0 && (*/}
                    {/*    <div className="form-error">{errors["name"].join(",")}</div>*/}
                    {/*)}*/}
                </div>
                <div className="form-row">
                    <label>Description</label>
                    <input
                        type="text"
                        placeholder="please input description"
                        name="description"
                        value={formValue.description}
                        onChange={onInputChange}
                    />
                    {/*{errors["profession"] && errors["profession"].length > 0 && (*/}
                    {/*    <div className="form-error">{errors["profession"].join(",")}</div>*/}
                    {/*)}*/}
                </div>
                <div className="form-row">
                    <label>Type</label>
                    <select name="type" value={formValue.type} onChange={e => {
                        const {name, value} = e.target;
                        setFormValue({...formValue, [name]: value})
                    }}>
                        {
                            enumToArray(TemplateType).map(({key, value}) => {
                                console.log(`${key}: ${value}`);
                                return (<option value={key}> {value} </option>);
                            })
                        }
                    </select>
                    {/*{errors["age"] && errors["age"].length > 0 && (*/}
                    {/*    <div className="form-error">{errors["age"].join(",")}</div>*/}
                    {/*)}*/}
                </div>
                <div className="form-row">
                    <label>Content</label>
                    <input
                        type="text"
                        placeholder="please input content"
                        name="content"
                        value={formValue.content}
                        onChange={onInputChange}
                    />
                    {/*{errors["age"] && errors["age"].length > 0 && (*/}
                    {/*    <div className="form-error">{errors["age"].join(",")}</div>*/}
                    {/*)}*/}
                </div>
                <div className="form-row">
                    <label>Context</label>
                    <input
                        type="text"
                        placeholder="please input context"
                        name="context"
                        value={formValue.context}
                        onChange={onInputChange}
                    />
                    {/*{errors["age"] && errors["age"].length > 0 && (*/}
                    {/*    <div className="form-error">{errors["age"].join(",")}</div>*/}
                    {/*)}*/}
                </div>
                <div className="form-row">
                    <button type="submit">Add new user</button>
                </div>
            </form>
        </div>
    );
}
export default TemplateEdit