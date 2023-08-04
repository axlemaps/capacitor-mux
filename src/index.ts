import { registerPlugin } from '@capacitor/core';

import type { CapacitorMuxPlugin } from './definitions';

const CapacitorMux = registerPlugin<CapacitorMuxPlugin>('CapacitorMux', {
  web: () => import('./web').then(m => new m.CapacitorMuxWeb()),
});

export * from './definitions';
export { CapacitorMux };
