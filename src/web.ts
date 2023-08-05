import { WebPlugin } from '@capacitor/core';

import type { CapacitorMuxPlugin } from './definitions';

export class CapacitorMuxWeb extends WebPlugin implements CapacitorMuxPlugin {
  async uploadVideo(): Promise<{ success: boolean }> {
    throw this.unimplemented('Not implemented on web.');
  }
}
