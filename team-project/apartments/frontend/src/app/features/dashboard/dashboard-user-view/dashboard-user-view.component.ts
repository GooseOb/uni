import {Component} from '@angular/core';
import {UserServiceService, User} from "../../../core/services/user-service.service";
import {HttpClient} from "@angular/common/http";
import {PhotoServiceService} from "../../../core/services/photo-service.service";
import {AppModule} from "../../../app.module";

@Component({
  selector: 'app-dashboard-user-view',
  templateUrl: './dashboard-user-view.component.html',
  styleUrls: ['./dashboard-user-view.component.css']
})
export class DashboardUserViewComponent {
  user: User;
  photo: File | null = null;
  change: string | undefined;

  constructor(
    private http: HttpClient,
    private userService: UserServiceService,
    private photoService: PhotoServiceService
  ) {
    this.user = userService.getCurrentUser();
  }

  onSubmit() {
    this.user.password = this.user.notHashedPassword;
    if (this.change == 'photo' && this.photo) {
      this.photoService.uploadUserPhoto(this.user, this.photo).subscribe(() => {
        this.userService.updateUser(this.userService.getCurrentUser(), this.user).subscribe(() => {
            this.userService.reloadUser(this.user.username, this.user.notHashedPassword)
            this.change = "";
          })
        }
      );
    } else {
      this.userService.updateUser(this.userService.getCurrentUser(), this.user).subscribe(() => {
        this.userService.reloadUser(this.user.username, this.user.notHashedPassword)
        this.change = "";
      });
    }
  }

  handleFileInput(event: Event) {
    const target = event.target as HTMLInputElement;
    const files: FileList | null = target.files;
    if (files) {
      this.photo = files.item(0);
    } else {
      this.photo = null;
    }
  }


  protected readonly console = console;
  protected readonly event = event;
  protected readonly AppModule = AppModule;
}
