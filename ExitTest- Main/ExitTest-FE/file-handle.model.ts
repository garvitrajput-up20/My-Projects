import { SafeUrl } from "@angular/platform-browser";

export interface FileHandle{
    forEach(arg0: (image: FileHandle) => void): unknown;
    width: number;
    height: number;
    file:File;
    url:SafeUrl
}