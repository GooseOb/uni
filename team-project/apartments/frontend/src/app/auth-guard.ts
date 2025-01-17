import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import {UserServiceService} from './core/services/user-service.service';

export const isAuthGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);
  const isAuthenticated = !!inject(UserServiceService).getCurrentUser();

  return isAuthenticated || router.navigate(['/login']);
};
