import { Interest } from "./interest.model"

export type User = {
    name?: String,
    email?: String,
    password?: String,
    city?: String,
    phone?: number,
    interest?: String[],
    role?: String
}