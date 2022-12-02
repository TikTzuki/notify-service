import {BaseModel} from "./interfaces";
import {FormValue} from "../validator";

export interface Template extends BaseModel{
    id: string;
    title: string;
    description: string;
    type: string;
    content: string;
    context: object
}